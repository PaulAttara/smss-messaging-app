/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 53 "../../../../../SSMS.ump"
public class Operand
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Operand Associations
  private Condition condition;
  private List<Message> messages;
  private List<Fragment> fragments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Operand()
  {
    messages = new ArrayList<Message>();
    fragments = new ArrayList<Fragment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Condition getCondition()
  {
    return condition;
  }

  public boolean hasCondition()
  {
    boolean has = condition != null;
    return has;
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setCondition(Condition aNewCondition)
  {
    boolean wasSet = false;
    if (condition != null && !condition.equals(aNewCondition) && equals(condition.getOperand()))
    {
      //Unable to setCondition, as existing condition would become an orphan
      return wasSet;
    }

    condition = aNewCondition;
    Operand anOldOperand = aNewCondition != null ? aNewCondition.getOperand() : null;

    if (!this.equals(anOldOperand))
    {
      if (anOldOperand != null)
      {
        anOldOperand.condition = null;
      }
      if (condition != null)
      {
        condition.setOperand(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMessages()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addMessage(Message aMessage)
  {
    boolean wasAdded = false;
    if (messages.contains(aMessage)) { return false; }
    messages.add(aMessage);
    if (aMessage.indexOfOperand(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMessage.addOperand(this);
      if (!wasAdded)
      {
        messages.remove(aMessage);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeMessage(Message aMessage)
  {
    boolean wasRemoved = false;
    if (!messages.contains(aMessage))
    {
      return wasRemoved;
    }

    int oldIndex = messages.indexOf(aMessage);
    messages.remove(oldIndex);
    if (aMessage.indexOfOperand(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMessage.removeOperand(this);
      if (!wasRemoved)
      {
        messages.add(oldIndex,aMessage);
      }
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFragments()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addFragment(Fragment aFragment)
  {
    boolean wasAdded = false;
    if (fragments.contains(aFragment)) { return false; }
    fragments.add(aFragment);
    if (aFragment.indexOfOperand(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aFragment.addOperand(this);
      if (!wasAdded)
      {
        fragments.remove(aFragment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeFragment(Fragment aFragment)
  {
    boolean wasRemoved = false;
    if (!fragments.contains(aFragment))
    {
      return wasRemoved;
    }

    int oldIndex = fragments.indexOf(aFragment);
    fragments.remove(oldIndex);
    if (aFragment.indexOfOperand(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aFragment.removeOperand(this);
      if (!wasRemoved)
      {
        fragments.add(oldIndex,aFragment);
      }
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

  public void delete()
  {
    Condition existingCondition = condition;
    condition = null;
    if (existingCondition != null)
    {
      existingCondition.delete();
      existingCondition.setOperand(null);
    }
    ArrayList<Message> copyOfMessages = new ArrayList<Message>(messages);
    messages.clear();
    for(Message aMessage : copyOfMessages)
    {
      aMessage.removeOperand(this);
    }
    ArrayList<Fragment> copyOfFragments = new ArrayList<Fragment>(fragments);
    fragments.clear();
    for(Fragment aFragment : copyOfFragments)
    {
      if (aFragment.numberOfOperands() <= Fragment.minimumNumberOfOperands())
      {
        aFragment.delete();
      }
      else
      {
        aFragment.removeOperand(this);
      }
    }
  }

}