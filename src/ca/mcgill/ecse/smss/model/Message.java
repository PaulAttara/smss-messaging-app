/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 45 "../../../../../SSMS.ump"
public class Message
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Message Attributes
  private String name;

  //Message Associations
  private SenderObject senderObject;
  private ReceiverObject receiverObject;
  private List<Operand> operands;
  private Element element;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Message(String aName, SenderObject aSenderObject, ReceiverObject aReceiverObject, Element aElement)
  {
    name = aName;
    boolean didAddSenderObject = setSenderObject(aSenderObject);
    if (!didAddSenderObject)
    {
      throw new RuntimeException("Unable to create message due to senderObject. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddReceiverObject = setReceiverObject(aReceiverObject);
    if (!didAddReceiverObject)
    {
      throw new RuntimeException("Unable to create message due to receiverObject. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    operands = new ArrayList<Operand>();
    boolean didAddElement = setElement(aElement);
    if (!didAddElement)
    {
      throw new RuntimeException("Unable to create message due to element. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne */
  public SenderObject getSenderObject()
  {
    return senderObject;
  }
  /* Code from template association_GetOne */
  public ReceiverObject getReceiverObject()
  {
    return receiverObject;
  }
  /* Code from template association_GetMany */
  public Operand getOperand(int index)
  {
    Operand aOperand = operands.get(index);
    return aOperand;
  }

  public List<Operand> getOperands()
  {
    List<Operand> newOperands = Collections.unmodifiableList(operands);
    return newOperands;
  }

  public int numberOfOperands()
  {
    int number = operands.size();
    return number;
  }

  public boolean hasOperands()
  {
    boolean has = operands.size() > 0;
    return has;
  }

  public int indexOfOperand(Operand aOperand)
  {
    int index = operands.indexOf(aOperand);
    return index;
  }
  /* Code from template association_GetOne */
  public Element getElement()
  {
    return element;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSenderObject(SenderObject aSenderObject)
  {
    boolean wasSet = false;
    if (aSenderObject == null)
    {
      return wasSet;
    }

    SenderObject existingSenderObject = senderObject;
    senderObject = aSenderObject;
    if (existingSenderObject != null && !existingSenderObject.equals(aSenderObject))
    {
      existingSenderObject.removeMessage(this);
    }
    senderObject.addMessage(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setReceiverObject(ReceiverObject aReceiverObject)
  {
    boolean wasSet = false;
    if (aReceiverObject == null)
    {
      return wasSet;
    }

    ReceiverObject existingReceiverObject = receiverObject;
    receiverObject = aReceiverObject;
    if (existingReceiverObject != null && !existingReceiverObject.equals(aReceiverObject))
    {
      existingReceiverObject.removeMessage(this);
    }
    receiverObject.addMessage(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOperands()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addOperand(Operand aOperand)
  {
    boolean wasAdded = false;
    if (operands.contains(aOperand)) { return false; }
    operands.add(aOperand);
    if (aOperand.indexOfMessage(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOperand.addMessage(this);
      if (!wasAdded)
      {
        operands.remove(aOperand);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeOperand(Operand aOperand)
  {
    boolean wasRemoved = false;
    if (!operands.contains(aOperand))
    {
      return wasRemoved;
    }

    int oldIndex = operands.indexOf(aOperand);
    operands.remove(oldIndex);
    if (aOperand.indexOfMessage(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOperand.removeMessage(this);
      if (!wasRemoved)
      {
        operands.add(oldIndex,aOperand);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOperandAt(Operand aOperand, int index)
  {  
    boolean wasAdded = false;
    if(addOperand(aOperand))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOperands()) { index = numberOfOperands() - 1; }
      operands.remove(aOperand);
      operands.add(index, aOperand);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOperandAt(Operand aOperand, int index)
  {
    boolean wasAdded = false;
    if(operands.contains(aOperand))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOperands()) { index = numberOfOperands() - 1; }
      operands.remove(aOperand);
      operands.add(index, aOperand);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOperandAt(aOperand, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setElement(Element aElement)
  {
    boolean wasSet = false;
    if (aElement == null)
    {
      return wasSet;
    }

    Element existingElement = element;
    element = aElement;
    if (existingElement != null && !existingElement.equals(aElement))
    {
      existingElement.removeMessage(this);
    }
    element.addMessage(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    SenderObject placeholderSenderObject = senderObject;
    this.senderObject = null;
    if(placeholderSenderObject != null)
    {
      placeholderSenderObject.removeMessage(this);
    }
    ReceiverObject placeholderReceiverObject = receiverObject;
    this.receiverObject = null;
    if(placeholderReceiverObject != null)
    {
      placeholderReceiverObject.removeMessage(this);
    }
    ArrayList<Operand> copyOfOperands = new ArrayList<Operand>(operands);
    operands.clear();
    for(Operand aOperand : copyOfOperands)
    {
      aOperand.removeMessage(this);
    }
    Element placeholderElement = element;
    this.element = null;
    if(placeholderElement != null)
    {
      placeholderElement.removeMessage(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "senderObject = "+(getSenderObject()!=null?Integer.toHexString(System.identityHashCode(getSenderObject())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "receiverObject = "+(getReceiverObject()!=null?Integer.toHexString(System.identityHashCode(getReceiverObject())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "element = "+(getElement()!=null?Integer.toHexString(System.identityHashCode(getElement())):"null");
  }
}