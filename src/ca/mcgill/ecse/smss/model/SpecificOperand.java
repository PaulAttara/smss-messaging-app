/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 50 "../../../../../SMSS.ump"
public class SpecificOperand
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes
  private int id;

  //SpecificOperand Associations
  private Message message;
  private Operand operand;
  private List<Fragment> fragments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificOperand(Operand aOperand)
  {
    id = nextId++;
    boolean didAddOperand = setOperand(aOperand);
    if (!didAddOperand)
    {
      throw new RuntimeException("Unable to create specificOperand due to operand. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    fragments = new ArrayList<Fragment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public Message getMessage()
  {
    return message;
  }

  public boolean hasMessage()
  {
    boolean has = message != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Operand getOperand()
  {
    return operand;
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
  /* Code from template association_SetOptionalOneToMany */
  public boolean setMessage(Message aMessage)
  {
    boolean wasSet = false;
    Message existingMessage = message;
    message = aMessage;
    if (existingMessage != null && !existingMessage.equals(aMessage))
    {
      existingMessage.removeSpecificOperand(this);
    }
    if (aMessage != null)
    {
      aMessage.addSpecificOperand(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOperand(Operand aOperand)
  {
    boolean wasSet = false;
    if (aOperand == null)
    {
      return wasSet;
    }

    Operand existingOperand = operand;
    operand = aOperand;
    if (existingOperand != null && !existingOperand.equals(aOperand))
    {
      existingOperand.removeSpecificOperand(this);
    }
    operand.addSpecificOperand(this);
    wasSet = true;
    return wasSet;
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
    if (aFragment.indexOfSpecificOperand(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aFragment.addSpecificOperand(this);
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
    if (aFragment.indexOfSpecificOperand(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aFragment.removeSpecificOperand(this);
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
    if (message != null)
    {
      Message placeholderMessage = message;
      this.message = null;
      placeholderMessage.removeSpecificOperand(this);
    }
    Operand placeholderOperand = operand;
    this.operand = null;
    if(placeholderOperand != null)
    {
      placeholderOperand.removeSpecificOperand(this);
    }
    ArrayList<Fragment> copyOfFragments = new ArrayList<Fragment>(fragments);
    fragments.clear();
    for(Fragment aFragment : copyOfFragments)
    {
      if (aFragment.numberOfSpecificOperands() <= Fragment.minimumNumberOfSpecificOperands())
      {
        aFragment.delete();
      }
      else
      {
        aFragment.removeSpecificOperand(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "message = "+(getMessage()!=null?Integer.toHexString(System.identityHashCode(getMessage())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "operand = "+(getOperand()!=null?Integer.toHexString(System.identityHashCode(getOperand())):"null");
  }
}