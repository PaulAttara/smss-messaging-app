/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 40 "../../../../../SMSS.ump"
public class ReceiverObject extends Object
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ReceiverObject Associations
  private List<Message> messages;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ReceiverObject(ClassType aClassType)
  {
    super(aClassType);
    messages = new ArrayList<Message>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMessages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Message addMessage(String aName, SenderObject aSenderObject)
  {
    return new Message(aName, aSenderObject, this);
  }

  public boolean addMessage(Message aMessage)
  {
    boolean wasAdded = false;
    if (messages.contains(aMessage)) { return false; }
    ReceiverObject existingReceiverObject = aMessage.getReceiverObject();
    boolean isNewReceiverObject = existingReceiverObject != null && !this.equals(existingReceiverObject);
    if (isNewReceiverObject)
    {
      aMessage.setReceiverObject(this);
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
    //Unable to remove aMessage, as it must always have a receiverObject
    if (!this.equals(aMessage.getReceiverObject()))
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
    for(int i=messages.size(); i > 0; i--)
    {
      Message aMessage = messages.get(i - 1);
      aMessage.delete();
    }
    super.delete();
  }

}