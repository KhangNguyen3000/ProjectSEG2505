package com.example.nguye.seg2505app.ScheduleClasses;

public class DailySchedule {

    private TimeNode head;
    private int size;

    /**
     * Empty constructor
     * Used tu build a new DailySchedule from 0 when merging a DailySchedule with a TimeSlot.
     */
    public DailySchedule() {
        head = new TimeNode(0, null, null);
//		head.prev = head.next = head;
        size = 0;
    }

    /**
     * Constructor
     * Creates a 4-nodes schedule using the 2 nodes from the DefaultSchedule and adding 2 extreme nodes.
     * Used to create the starting point to build the final DailySchedule.
     * @param startTime
     * @param endTime
     * @param state
     */
    public DailySchedule(int startTime, int endTime, ScheduleState state) {
        head = new TimeNode(0, null, null); // set next after initialization of node1
        TimeNode node1 = new TimeNode(0, null, ScheduleState.UNAVAILABLE); // set next after initialization of node2
        TimeNode node2 = new TimeNode(startTime, null, state); // set next after initialization of node3
        TimeNode node3 = new TimeNode(endTime, null, ScheduleState.UNAVAILABLE); // set next after initialization of node4
        TimeNode node4 = new TimeNode(1440, null, null);

        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        size = 4;
    }



//	public void add(TimeNode timeNode) {
//		head.next = timeNode;
//		head.state = timeNode.prevState;
//		size++;
//	}

//	public void append(TimeNode newTimeNode, boolean hasPriority) {
//		TimeNode last = this.head;
//		// get to the end of the list
//		while (last.next != null) {
//			last = last.next;
//		}
//		last.next = newTimeNode;
//		newTimeNode.prev = last;
//		newTimeNode.next = null;
////		newTimeNode.prevState = lat
//
//		// COME BACK LATER
//	}


    public void append(TimeNode newTimeNode) {
        TimeNode last = this.head;
        while (last.getNext() != null) { // get to the end of the chain
            last = last.getNext();
        }
        last.setNext(newTimeNode);
        newTimeNode.setNext(null);
    }



//	  // add a new integer (most efficient)
//	  public boolean add(int value) {
//
//	    Node newNode= new Node(value, head, head.next);
//		head.next.prev= newNode;
//		head.next= newNode;
//
//		size++;
//
//	    return true;
//	  }





//	public TimeNode extract() {
//		if (this.head.next == null) {
//			return null;
//		} else {
//			TimeNode first = this.head.next;
//			this.head.next = first.next;
//			first.next.prev = head;
//			first.prev = null;
//			first.next = null;
//			return first;
//		}
//
//	}


//	/**
//	 * Get the first TimeNode of the com.example.vincent.testing.ScheduleManager.DailySchedule.
//	 * @return
//	 */
//	public TimeNode getFirst() {
//		return this.head.next;
//	}

    /**
     * Get the last TimeNode of the com.example.vincent.testing.ScheduleManager.DailySchedule.
     * @return
     */
    public TimeNode getLast() {
        TimeNode last = head;
        while (last.getNext() != null) {
            last = last.getNext();
        }
        return last;
    }

    public TimeNode extractFirst() {
        if (this.head.getNext() == null) {
            return null;
        } else {
            TimeNode first = this.head.getNext();
            this.head.setNext(first.getNext());
            first.setNext(null);
            return first;
        }
    }

    public DailySchedule merge(TimeSlot timeSlot) {
        // Initialize a blank DailySchedule.
        DailySchedule newDS = new DailySchedule();

        // Append all the TimeNodes that are earlier than the startNode of the TimeSlot.
        while (this.head.getNext().getTime() < timeSlot.getStartTime()) {
//			TimeNode first = this.extractFirst();
//			newDS.append(first);
            newDS.append(this.extractFirst());

//			this.head.next = this.head.next.next;
//			this.head.next.next.prev
//			this.head.next.prev = newDS.getLast();
//			newDS.getLast().next =
        }

        // If a DailySchedule's TimeNode and a TimeSlot's TimeNode have the same time, the TimeSlot has priority
        if (timeSlot.getStartTime() == this.head.getNext().getTime()) {
            // Append the timeSlot's startNode and remove the next TimeNode of the current DailySchedule.
            newDS.append(timeSlot.getStartNode());
            this.extractFirst(); // Don't do anything with it, just remove it.
        } else {
            newDS.append(timeSlot.getStartNode());
        }

        // Ignore all subsequent TimeNodes that are earlier or equal to the TimeSlot's endTime,
        //	but copy the state of the last ignored node in the endTime TimeNode.
        while (this.head.getNext().getTime() <= timeSlot.getEndTime()) {
            timeSlot.getEndNode().setState(this.head.getNext().getState()); // Copy the state of the last ignored node.
            this.extractFirst();
        }
        newDS.append(timeSlot.getEndNode());

        // Append the rest of the TimeNodes of the com.example.vincent.testing.ScheduleManager.DailySchedule
        while (this.head.getNext() != null) {
            newDS.append(this.extractFirst());
        }

        newDS.cleanUp();
        return newDS;
    }

    public boolean isBookedBetween(int startTime, int endTime) {
        TimeNode currentNode = this.head;
        if (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }

//        // Find the first BOOKED node
//        while (currentNode.getState() != ScheduleState.BOOKED) {
//            if (currentNode.getNext() != null) {
//                currentNode = currentNode.getNext();
//            }
//        }

        // Find the first BOOKED node since the others have no interest.
        while (currentNode.getNext() != null) {
            if (currentNode.getState() == ScheduleState.BOOKED) {
                break;
            }
            currentNode = currentNode.getNext();
        }
        // Check if there is any overlap between the specified time slot and a BOOKED state.
        while (currentNode.getNext() != null) {
            if (currentNode.getState() == ScheduleState.BOOKED) {
                if (((currentNode.getTime() >= startTime) // The currentNode is between startTime
                        && (currentNode.getTime() < endTime)) // and endTime, or
                        || ((currentNode.getTime() < startTime) // startTime is between the 2 nodes
                        && (currentNode.getNext().getTime() > startTime))) {
                    return  true;
                }
            }
            currentNode = currentNode.getNext();
        }
//        while (currentNode.getTime() < endTime) {
//            if (currentNode.getState() == ScheduleState.BOOKED) {
//                if (currentNode.getTime() >= startTime) {
//                    // && (currentNode.getTime() < endTime)
//                    return true;
//                }
//            }
//            if (currentNode.getNext() != null) {
//                currentNode = currentNode.getNext();
//            }
//        }
        return false;
    }

//	/**
//	 * Return the TimeNode with the earliest time. If they are equal, return the second one.
//	 * @param timeNode1
//	 * @param timeNode2
//	 * @return
//	 */
//	public TimeNode earliest(TimeNode timeNode1, TimeNode timeNode2) {
//		if (timeNode1.time == timeNode2.time) {
//			return timeNode2;
//		} else if (timeNode1.time < timeNode2.time) {
//			return timeNode1;
//		} else {
//			return timeNode2;
//		}
//	}




    public DailySchedule cleanUp() {
        TimeNode currentNode = this.head;
        TimeNode nextNode = currentNode.getNext();
        while (nextNode != null) {
            if (currentNode.getState() == nextNode.getState()) {
                currentNode.removeNext();
                nextNode = currentNode.getNext();
            } else {
                currentNode = nextNode;
                nextNode = nextNode.getNext();
            }
        }
        return this;
    }















//
//	   // add a new integer (least efficient)
//	  public boolean addOppositeSide(int value) {
//
//	    Node newNode= new Node(value, head.prev, head);
//		head.prev.next= newNode;
//		head.prev= newNode;
//
//		size++;
//
//	    return true;
//	  }

//	  // search if a given integer is in the array
//	  public boolean search(int value) {
//
//	    Node n= head.next;
//
//	    while (n!=head && n.element!=value)
//		  n= n.next;
//
//		if (n==head)
//		  return false;
//		else
//		  return true;
//	  }
//
//	  // remove a given integer (first occurrence of)
//	  public boolean searchAndRemove(int value) {
//
//		Node n= head.next;
//
//	    while (n!=head && n.element!=value)
//		  n= n.next;
//
//
//	    if (n!=head) {
//
//		  n.prev.next= n.next;
//		  n.next.prev= n.prev;
//		  size--;
//	      return true;
//
//		} else {
//
//		  return false;
//		}
//	  }
//
//	  // remove element at a given index
//	  public boolean removeAt(int index) {
//
//	    if (index<0 || index>=size)
//		  return false;
//
//		size--;
//
//		Node n= head.next;
//	    for (int i=0; i<index; i++)
//		  n= n.next;
//
//		n.prev.next= n.next;
//		n.next.prev= n.prev;
//
//		return true;
//	  }
//
//
//
//	  // string representation
//	  public String toString() {
//
//	    StringBuffer s = new StringBuffer("");
//
//	    for (Node node= head.next; node!= head; node= node.next) {
//		  s.append("["+node.element+"]");
//		}
//
//		s.append("("+size+")");
//
//		return s.toString();
//	  }
//
//	  public static void main(String[] args) {
//
//	    DoublyLinkedList list= new DoublyLinkedList();
//
//		list.add(34);
//		list.add(93);
//		list.add(67);
//		list.add(23);
//		list.add(51);
//		System.out.println("A:" + list);
//
//		list.addOppositeSide(33);
//		System.out.println("B:" + list);
//
//		list.searchAndRemove(51);
//		System.out.println("C:" + list);
//		list.searchAndRemove(67);
//		System.out.println("D:" + list);
//
//		System.out.println("E1:" + list.search(93));
//		System.out.println("E2:" + list.search(11));
//
//		list.removeAt(1);
//		System.out.println("F1:" + list);
//		list.removeAt(0);
//		System.out.println("F2:" + list);
//		list.removeAt(list.getSize()-1);
//		System.out.println("F3:" + list);
//
//	  }

    public int getSize() {
        return this.size;
    }

    public String toString() {
        TimeNode timeNode = this.head.getNext();
        String schedule = "Schedule's size: " + this.size + "\n";
        while (timeNode != null) {
            schedule = schedule + timeNode.toString();
            timeNode = timeNode.getNext();
        }
        return schedule;
    }
}
