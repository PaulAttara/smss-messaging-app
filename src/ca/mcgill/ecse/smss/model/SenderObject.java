/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 35 "../../../../../SMSS.ump"
public class SenderObject extends Object
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SenderObject Attributes
  private String name;

  //SenderObject Associations
  private SMSS sMSS;
  private List<Message> messages;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SenderObject(ClassType aClassType, String aName, SMSS aSMSS)
  {
    super(aClassType);
    name = aName;
    boolean didAddSMSS = setSMSS(aSMSS);
    if (!didAddSMSS)
    {
      throw new RuntimeException("Unable to create senderObject due to sMSS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    messages = new ArrayList<Message>();
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
  public SMSS getSMSS()
  {
    return sMSS;
  }
  /* Code from template association_GetMany */
  public Message getMessage(int index)
  {
    Message aMessage = messages.get(index);
    return aMessage;
  }

  public List<Message> getMessages()
  {
    List<Message> newMessages = Collections.unmodifiableList(messages);
    return newMessages;
  }

  public int numberOfMessages()
  {
    int number = messages.size();
    return number;
  }

  public boolean hasMessages()
  {
    boolean has = messages.size() > 0;
    return has;
  }

  public int indexOfMessage(Message aMessage)
  {
    int index = messages.indexOf(aMessage);
    return index;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setSMSS(SMSS aNewSMSS)
  {
    boolean wasSet = false;
    if (aNewSMSS == null)
    {
      //Unable to setSMSS to null, as senderObject must always be associated to a sMSS
      return wasSet;
    }
    
    SenderObject existingSenderObject = aNewSMSS.getSenderObject();
    if (existingSenderObject != null && !equals(existingSenderObject))
    {
      //Unable to setSMSS, the current sMSS already has a senderObject, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    SMSS anOldSMSS = sMSS;
    sMSS = aNewSMSS;
    sMSS.setSenderObject(this);

    if (anOldSMSS != null)
    {
      anOldSMSS.setSenderObject(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMessages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Message addMessage(String aName, ReceiverObject aReceiverObject, Element aElement)
  {
    return new Message(aName, this, aReceiverObject, aElement);
  }

  public boolean addMessage(Message aMessage)
  {
    boolean wasAdded = false;
    if (messages.contains(aMessage)) { return false; }
    SenderObject existingSenderObject = aMessage.getSenderObject();
    boolean isNewSenderObject = existingSenderObject != null && !this.equals(existingSenderObject);
    if (isNewSenderObject)
    {
      aMessage.setSenderObject(this);
    }
    else
    {
      messages.add(aMessage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMessage(Message aMessage)
  {
    boolean wasRemoved = false;
    //Unable to remove aMessage, as it must always have a senderObject
    if (!this.equals(aMessage.getSenderObject()))
    {
      messages.remove(aMessage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMessageAt(Message aMessage, int index)
  {  
    boolean wasAdded = false;
    if(addMessage(aMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessages()) { index = numberOfMessages() - 1; }
      messages.remove(aMessage);
      messages.add(index, aMessage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMessageAt(Message aMessage, int index)
  {
    boolean wasAdded = false;
    if(messages.contains(aMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessages()) { index = numberOfMessages() - 1; }
      messages.remove(aMessage);
      messages.add(index, aMessage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMessageAt(aMessage, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    SMSS existingSMSS = sMSS;
    sMSS = null;
    if (existingSMSS != null)
    {
      existingSMSS.setSenderObject(null);
    }
    for(int i=messages.size(); i > 0; i--)
    {
      Message aMessage = messages.get(i - 1);
      aMessage.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sMSS = "+(getSMSS()!=null?Integer.toHexString(System.identityHashCode(getSMSS())):"null");
  }
}