/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 57 "../../../../../SMSS.ump"
public class Message
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Message> messagesByName = new HashMap<String, Message>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Message Attributes
  private String name;

  //Message Associations
  private SenderObject senderObject;
  private ReceiverObject receiverObject;
  private List<SpecificElement> specificElements;
  private List<SpecificOperand> specificOperands;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Message(String aName, SenderObject aSenderObject, ReceiverObject aReceiverObject)
  {
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
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
    specificElements = new ArrayList<SpecificElement>();
    specificOperands = new ArrayList<SpecificOperand>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      messagesByName.remove(anOldName);
    }
    messagesByName.put(aName, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static Message getWithName(String aName)
  {
    return messagesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
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
  public SpecificElement getSpecificElement(int index)
  {
    SpecificElement aSpecificElement = specificElements.get(index);
    return aSpecificElement;
  }

  public List<SpecificElement> getSpecificElements()
  {
    List<SpecificElement> newSpecificElements = Collections.unmodifiableList(specificElements);
    return newSpecificElements;
  }

  public int numberOfSpecificElements()
  {
    int number = specificElements.size();
    return number;
  }

  public boolean hasSpecificElements()
  {
    boolean has = specificElements.size() > 0;
    return has;
  }

  public int indexOfSpecificElement(SpecificElement aSpecificElement)
  {
    int index = specificElements.indexOf(aSpecificElement);
    return index;
  }
  /* Code from template association_GetMany */
  public SpecificOperand getSpecificOperand(int index)
  {
    SpecificOperand aSpecificOperand = specificOperands.get(index);
    return aSpecificOperand;
  }

  public List<SpecificOperand> getSpecificOperands()
  {
    List<SpecificOperand> newSpecificOperands = Collections.unmodifiableList(specificOperands);
    return newSpecificOperands;
  }

  public int numberOfSpecificOperands()
  {
    int number = specificOperands.size();
    return number;
  }

  public boolean hasSpecificOperands()
  {
    boolean has = specificOperands.size() > 0;
    return has;
  }

  public int indexOfSpecificOperand(SpecificOperand aSpecificOperand)
  {
    int index = specificOperands.indexOf(aSpecificOperand);
    return index;
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
  public static int minimumNumberOfSpecificElements()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addSpecificElement(SpecificElement aSpecificElement)
  {
    boolean wasAdded = false;
    if (specificElements.contains(aSpecificElement)) { return false; }
    Message existingMessage = aSpecificElement.getMessage();
    if (existingMessage == null)
    {
      aSpecificElement.setMessage(this);
    }
    else if (!this.equals(existingMessage))
    {
      existingMessage.removeSpecificElement(aSpecificElement);
      addSpecificElement(aSpecificElement);
    }
    else
    {
      specificElements.add(aSpecificElement);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificElement(SpecificElement aSpecificElement)
  {
    boolean wasRemoved = false;
    if (specificElements.contains(aSpecificElement))
    {
      specificElements.remove(aSpecificElement);
      aSpecificElement.setMessage(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificElementAt(SpecificElement aSpecificElement, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificElement(aSpecificElement))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificElements()) { index = numberOfSpecificElements() - 1; }
      specificElements.remove(aSpecificElement);
      specificElements.add(index, aSpecificElement);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificElementAt(SpecificElement aSpecificElement, int index)
  {
    boolean wasAdded = false;
    if(specificElements.contains(aSpecificElement))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificElements()) { index = numberOfSpecificElements() - 1; }
      specificElements.remove(aSpecificElement);
      specificElements.add(index, aSpecificElement);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificElementAt(aSpecificElement, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificOperands()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addSpecificOperand(SpecificOperand aSpecificOperand)
  {
    boolean wasAdded = false;
    if (specificOperands.contains(aSpecificOperand)) { return false; }
    Message existingMessage = aSpecificOperand.getMessage();
    if (existingMessage == null)
    {
      aSpecificOperand.setMessage(this);
    }
    else if (!this.equals(existingMessage))
    {
      existingMessage.removeSpecificOperand(aSpecificOperand);
      addSpecificOperand(aSpecificOperand);
    }
    else
    {
      specificOperands.add(aSpecificOperand);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificOperand(SpecificOperand aSpecificOperand)
  {
    boolean wasRemoved = false;
    if (specificOperands.contains(aSpecificOperand))
    {
      specificOperands.remove(aSpecificOperand);
      aSpecificOperand.setMessage(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificOperandAt(SpecificOperand aSpecificOperand, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificOperand(aSpecificOperand))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificOperands()) { index = numberOfSpecificOperands() - 1; }
      specificOperands.remove(aSpecificOperand);
      specificOperands.add(index, aSpecificOperand);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificOperandAt(SpecificOperand aSpecificOperand, int index)
  {
    boolean wasAdded = false;
    if(specificOperands.contains(aSpecificOperand))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificOperands()) { index = numberOfSpecificOperands() - 1; }
      specificOperands.remove(aSpecificOperand);
      specificOperands.add(index, aSpecificOperand);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificOperandAt(aSpecificOperand, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    messagesByName.remove(getName());
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
    while( !specificElements.isEmpty() )
    {
      specificElements.get(0).setMessage(null);
    }
    while( !specificOperands.isEmpty() )
    {
      specificOperands.get(0).setMessage(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "senderObject = "+(getSenderObject()!=null?Integer.toHexString(System.identityHashCode(getSenderObject())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "receiverObject = "+(getReceiverObject()!=null?Integer.toHexString(System.identityHashCode(getReceiverObject())):"null");
  }
}