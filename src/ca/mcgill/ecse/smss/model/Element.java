/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 9 "../../../../../SSMS.ump"
public class Element
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Element Attributes
  private int id;

  //Element Associations
  private List<Fragment> fragments;
  private Method method;
  private List<Message> messages;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Element(int aId, Method aMethod)
  {
    id = aId;
    fragments = new ArrayList<Fragment>();
    boolean didAddMethod = setMethod(aMethod);
    if (!didAddMethod)
    {
      throw new RuntimeException("Unable to create element due to method. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    messages = new ArrayList<Message>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetMany */
  public Fragment getFragment(int index)
  {
    Fragment aFragment = fragments.get(index);
    return aFragment;
  }

  public List<Fragment> getFragments()
  {
    List<Fragment> newFragments = Collections.unmodifiableList(fragments);
    return newFragments;
  }

  public int numberOfFragments()
  {
    int number = fragments.size();
    return number;
  }

  public boolean hasFragments()
  {
    boolean has = fragments.size() > 0;
    return has;
  }

  public int indexOfFragment(Fragment aFragment)
  {
    int index = fragments.indexOf(aFragment);
    return index;
  }
  /* Code from template association_GetOne */
  public Method getMethod()
  {
    return method;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFragments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addFragment(Fragment aFragment)
  {
    boolean wasAdded = false;
    if (fragments.contains(aFragment)) { return false; }
    Element existingElement = aFragment.getElement();
    boolean isNewElement = existingElement != null && !this.equals(existingElement);
    if (isNewElement)
    {
      aFragment.setElement(this);
    }
    else
    {
      fragments.add(aFragment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFragment(Fragment aFragment)
  {
    boolean wasRemoved = false;
    //Unable to remove aFragment, as it must always have a element
    if (!this.equals(aFragment.getElement()))
    {
      fragments.remove(aFragment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFragmentAt(Fragment aFragment, int index)
  {  
    boolean wasAdded = false;
    if(addFragment(aFragment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFragments()) { index = numberOfFragments() - 1; }
      fragments.remove(aFragment);
      fragments.add(index, aFragment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFragmentAt(Fragment aFragment, int index)
  {
    boolean wasAdded = false;
    if(fragments.contains(aFragment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFragments()) { index = numberOfFragments() - 1; }
      fragments.remove(aFragment);
      fragments.add(index, aFragment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFragmentAt(aFragment, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMethod(Method aNewMethod)
  {
    boolean wasSet = false;
    if (aNewMethod == null)
    {
      //Unable to setMethod to null, as element must always be associated to a method
      return wasSet;
    }
    
    Element existingElement = aNewMethod.getElement();
    if (existingElement != null && !equals(existingElement))
    {
      //Unable to setMethod, the current method already has a element, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Method anOldMethod = method;
    method = aNewMethod;
    method.setElement(this);

    if (anOldMethod != null)
    {
      anOldMethod.setElement(null);
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
  public Message addMessage(String aName, SenderObject aSenderObject, ReceiverObject aReceiverObject)
  {
    return new Message(aName, aSenderObject, aReceiverObject, this);
  }

  public boolean addMessage(Message aMessage)
  {
    boolean wasAdded = false;
    if (messages.contains(aMessage)) { return false; }
    Element existingElement = aMessage.getElement();
    boolean isNewElement = existingElement != null && !this.equals(existingElement);
    if (isNewElement)
    {
      aMessage.setElement(this);
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
    //Unable to remove aMessage, as it must always have a element
    if (!this.equals(aMessage.getElement()))
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
    for(int i=fragments.size(); i > 0; i--)
    {
      Fragment aFragment = fragments.get(i - 1);
      aFragment.delete();
    }
    Method existingMethod = method;
    method = null;
    if (existingMethod != null)
    {
      existingMethod.setElement(null);
    }
    for(int i=messages.size(); i > 0; i--)
    {
      Message aMessage = messages.get(i - 1);
      aMessage.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "method = "+(getMethod()!=null?Integer.toHexString(System.identityHashCode(getMethod())):"null");
  }
}