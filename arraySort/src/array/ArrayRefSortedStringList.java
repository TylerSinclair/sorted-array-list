package array;

//----------------------------------------------------------------
//Tyler Sinclair
//100924537
//-------------------------------------------------------------

public class ArrayRefSortedStringList
{
protected static final int NUL = -1;

    protected class AListNode
    {
        private String info;
        private int next;
    }

protected AListNode[] nodes;

protected int list;
protected int free;

protected int numElements;
protected int currentPos;

protected boolean found;
protected int location;
protected int previous;

public ArrayRefSortedStringList(int maxElements){
	nodes = new AListNode[maxElements];
	for (int index = 0; index < maxElements; index++)
		nodes[index] = new AListNode();

	for (int index = 1; index < maxElements; index++)
		nodes[index - 1].next = index;
	nodes[maxElements - 1].next = NUL;

	list = NUL;
	free = 0;
	numElements = 0;
	currentPos = NUL;
}

protected int getNode(){
	int hold;
	hold = free;
	free = nodes[free].next;
	return hold;
}

protected void freeNode(int index){
	nodes[index].next = free;
	free = index;
}


public boolean isFull(){
	return (free == NUL);
}

public boolean remove (String element){
	int hold;
	find(element);
	if (found){
		hold = location;
		if (list == location)
			list = nodes[list].next;
		else
			nodes[previous].next = nodes[location].next;
		freeNode(hold);
		numElements--;
	}
return found;
}

private void find(String element) {
	reset();
	if(element.compareTo(nodes[list].info.toLowerCase().trim()) == 0){
		location = list;
		found = true;
	}else{
		for(int i = 0; i < numElements -1 ; i++){
			if(element.toLowerCase().compareTo(getNext()) == 0){
				location = currentPos;
				found = true;
				break;
			}
		}
	}
}


public int size() {
	return numElements;
}


public void add(String element) {
	String elementLow = element.toLowerCase().trim();
	if(!isFull()){
		int nextNode = getNode();
		if(numElements == 0){
			nodes[nextNode].info = element;
			nodes[nextNode].next = NUL;
			list = nextNode;
		}else{
			if(elementLow.compareTo(nodes[list].info.toLowerCase().trim()) < 0){
				nodes[nextNode].info = element;
				nodes[nextNode].next = list;
				list = nextNode;
			}else if(numElements == 2){
				reset();
				if(elementLow.compareTo(getNext().toLowerCase().trim()) < 0){
					nodes[list].next = nextNode;
					nodes[nextNode].info = element;
					nodes[nextNode].next = currentPos;
					nodes[currentPos].next = NUL;
				}else{
					nodes[currentPos].next = nextNode;
					nodes[nextNode].info = element;
					nodes[nextNode].next = NUL;
				}
			}else{
				reset();
				String replace = nodes[list].info.toLowerCase().trim();
				 for(int i = 0; i < numElements-1; i++){
					if(elementLow.compareTo(replace) > 0){
						replace = getNext().toLowerCase().trim();
					}
				 }
				 find(replace);
				 if(found){
					if(nodes[location].next != NUL){
						nodes[previous].next = nextNode;
						nodes[nextNode].next = currentPos;
						nodes[nextNode].info = element;
					}else{
						nodes[location].next = nextNode;
						nodes[nextNode].next = NUL;
						nodes[nextNode].info = element;
					}
				 }
			}
		}
		numElements++;
	}
}


public boolean contains(String element) {
	find(element);
	return found;
}


public String get(String element) {
	find(element);
	if(found)
		return nodes[location].info;
	else
		return null;
}


public void reset() {
	found = false;
	previous = NUL;
	currentPos = list;
	location = NUL;
}


public String getNext() {
	if(nodes[currentPos].next != NUL){
	previous = currentPos;
	currentPos = nodes[currentPos].next;
	return nodes[currentPos].info;
	}else{
		previous = currentPos;
		currentPos = list;
		return nodes[currentPos].info;
	}
}

@Override
public String toString() {
	String theReturn = "The Students list: \n";
	reset();
	if(numElements == 0)
		return "The list is empty!";
	else{
		theReturn += nodes[list].info + "\n";
		for(int i = 0;i < numElements-1;i++)
			theReturn += getNext() + "\n";
		return theReturn;
	}
}

}