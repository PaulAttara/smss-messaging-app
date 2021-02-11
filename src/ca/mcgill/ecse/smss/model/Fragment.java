/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 65 "../../../../../SMSS.ump"
public abstract class Fragment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Fragment Associations
  private List<SpecificMessage> specificMessages;
  private SpecificElement specificElement;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Fragment(SpecificElement aSpecificElement, SpecificMessage... allSpecificMessages)
  {
    specificMessages = new ArrayList<SpecificMessage>();
    boolean didAddSpecificMessages = setSpecificMessages(allSpecificMessages);
    if (!didAddSpecificMessages)
    {
      throw new RuntimeException("Unable to create Fragment, must have at least 2 specificMessages. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSpecificElement = setSpecificElement(aSpecificElement);
    if (!didAddSpecificElement)
    {
      throw new RuntimeException("Unable to create fragment due to specificElement. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public SpecificMessage getSpecificMessage(int index)
  {
    SpecificMessage aSpecificMessage = specificMessages.get(index);
    return aSpecificMessage;
  }

  public List<SpecificMessage> getSpecificMessages()
  {
    List<SpecificMessage> newSpecificMessages = Collections.unmodifiableList(specificMessages);
    return newSpecificMessages;
  }

  public int numberOfSpecificMessages()
  {
    int number = specificMessages.size();
    return number;
  }

  public boolean hasSpecificMessages()
  {
    boolean has = specificMessages.size() > 0;
    return has;
  }

  public int indexOfSpecificMessage(SpecificMessage aSpecificMessage)
  {
    int index = specificMessages.indexOf(aSpecificMessage);
    return index;
  }
  /* Code from template association_GetOne */
  public SpecificElement getSpecificElement()
  {
    return specificElement;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfSpecificMessagesValid()
  {
    boolean isValid = numberOfSpecificMessages() >= minimumNumberOfSpecificMessages();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificMessages()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addSpecificMessage(SpecificMessage aSpecificMessage)
  {
    boolean wasAdded = false;
    if (specificMessages.contains(aSpecificMessage)) { return false; }
    specificMessages.add(aSpecificMessage);
    if (aSpecificMessage.indexOfFragment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSpecificMessage.addFragment(this);
      if (!wasAdded)
      {
        specificMessages.remove(aSpecificMessage);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeSpecificMessage(SpecificMessage aSpecificMessage)
  {
    boolean wasRemoved = false;
    if (!specificMessages.contains(aSpecificMessage))
    {
      return wasRemoved;
    }

    if (numberOfSpecificMessages() <= minimumNumberOfSpecificMessages())
    {
      return wasRemoved;
    }

    int oldIndex = specificMessages.indexOf(aSpecificMessage);
    specificMessages.remove(oldIndex);
    if (aSpecificMessage.indexOfFragment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSpecificMessage.removeFragment(this);
      if (!wasRemoved)
      {
        specificMessages.add(oldIndex,aSpecificMessage);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setSpecificMessages(SpecificMessage... newSpecificMessages)
  {
    boolean wasSet = false;
    ArrayList<SpecificMessage> verifiedSpecificMessages = new ArrayList<SpecificMessage>();
    for (SpecificMessage aSpecificMessage : newSpecificMessages)
    {
      if (verifiedSpecificMessages.contains(aSpecificMessage))
      {
        continue;
      }
      verifiedSpecificMessages.add(aSpecificMessage);
    }

    if (verifiedSpecificMessages.size() != newSpecificMessages.length || verifiedSpecificMessages.size() < minimumNumberOfSpecificMessages())
    {
      return wasSet;
    }

    ArrayList<SpecificMessage> oldSpecificMessages = new ArrayList<SpecificMessage>(specificMessages);
    specificMessages.clear();
    for (SpecificMessage aNewSpecificMessage : verifiedSpecificMessages)
    {
      specificMessages.add(aNewSpecificMessage);
      if (oldSpecificMessages.contains(aNewSpecificMessage))
      {
        oldSpecificMessages.remove(aNewSpecificMessage);
      }
      else
      {
        aNewSpecificMessage.addFragment(this);
      }
    }

    for (SpecificMessage anOldSpecificMessage : oldSpecificMessages)
    {
      anOldSpecificMessage.removeFragment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificMessageAt(SpecificMessage aSpecificMessage, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificMessage(aSpecificMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificMessages()) { index = numberOfSpecificMessages() - 1; }
      specificMessages.remove(aSpecificMessage);
      specificMessages.add(index, aSpecificMessage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificMessageAt(SpecificMessage aSpecificMessage, int index)
  {
    boolean wasAdded = false;
    if(specificMessages.contains(aSpecificMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificMessages()) { index = numberOfSpecificMessages() - 1; }
      specificMessages.remove(aSpecificMessage);
      specificMessages.add(index, aSpecificMessage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificMessageAt(aSpecificMessage, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setSpecificElement(SpecificElement aNewSpecificElement)
  {
    boolean wasSet = false;
    if (aNewSpecificElement == null)
    {
      //Unable to setSpecificElement to null, as fragment must always be associated to a specificElement
      return wasSet;
    }
    
    Fragment existingFragment = aNewSpecificElement.getFragment();
    if (existingFragment != null && !equals(existingFragment))
    {
      //Unable to setSpecificElement, the current specificElement already has a fragment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    SpecificElement anOldSpecificElement = specificElement;
    specificElement = aNewSpecificElement;
    specificElement.setFragment(this);

    if (anOldSpecificElement != null)
    {
      anOldSpecificElement.setFragment(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (specificMessages.size() > 0)
    {
      SpecificMessage aSpecificMessage = specificMessages.get(specificMessages.size() - 1);
      aSpecificMessage.delete();
      specificMessages.remove(aSpecificMessage);
    }
    
    SpecificElement existingSpecificElement = specificElement;
    specificElement = null;
    if (existingSpecificElement != null)
    {
      existingSpecificElement.setFragment(null);
    }
  }

}