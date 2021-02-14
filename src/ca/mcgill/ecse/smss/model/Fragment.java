/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 69 "../../../../../SMSS.ump"
public abstract class Fragment
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

  //Fragment Associations
  private List<SpecificOperand> specificOperands;
  private SMSS sMSS;
  private List<SpecificElement> specificElements;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Fragment(SMSS aSMSS, SpecificOperand... allSpecificOperands)
  {
    id = nextId++;
    specificOperands = new ArrayList<SpecificOperand>();
    boolean didAddSpecificOperands = setSpecificOperands(allSpecificOperands);
    if (!didAddSpecificOperands)
    {
      throw new RuntimeException("Unable to create Fragment, must have at least 2 specificOperands. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSMSS = setSMSS(aSMSS);
    if (!didAddSMSS)
    {
      throw new RuntimeException("Unable to create fragment due to sMSS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    specificElements = new ArrayList<SpecificElement>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetMany */
  public SpecificOperand getSpecificOperand(int index)
  {
    SpecificOperand aSpecificOperand = specificOperands.get(index);
    return aSpecificOperand;
  }

  /**
   * 0..* <@>- 2..* SpecificOperand;
   */
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
  /* Code from template association_GetOne */
  public SMSS getSMSS()
  {
    return sMSS;
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfSpecificOperandsValid()
  {
    boolean isValid = numberOfSpecificOperands() >= minimumNumberOfSpecificOperands();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificOperands()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addSpecificOperand(SpecificOperand aSpecificOperand)
  {
    boolean wasAdded = false;
    if (specificOperands.contains(aSpecificOperand)) { return false; }
    specificOperands.add(aSpecificOperand);
    if (aSpecificOperand.indexOfFragment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSpecificOperand.addFragment(this);
      if (!wasAdded)
      {
        specificOperands.remove(aSpecificOperand);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeSpecificOperand(SpecificOperand aSpecificOperand)
  {
    boolean wasRemoved = false;
    if (!specificOperands.contains(aSpecificOperand))
    {
      return wasRemoved;
    }

    if (numberOfSpecificOperands() <= minimumNumberOfSpecificOperands())
    {
      return wasRemoved;
    }

    int oldIndex = specificOperands.indexOf(aSpecificOperand);
    specificOperands.remove(oldIndex);
    if (aSpecificOperand.indexOfFragment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSpecificOperand.removeFragment(this);
      if (!wasRemoved)
      {
        specificOperands.add(oldIndex,aSpecificOperand);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setSpecificOperands(SpecificOperand... newSpecificOperands)
  {
    boolean wasSet = false;
    ArrayList<SpecificOperand> verifiedSpecificOperands = new ArrayList<SpecificOperand>();
    for (SpecificOperand aSpecificOperand : newSpecificOperands)
    {
      if (verifiedSpecificOperands.contains(aSpecificOperand))
      {
        continue;
      }
      verifiedSpecificOperands.add(aSpecificOperand);
    }

    if (verifiedSpecificOperands.size() != newSpecificOperands.length || verifiedSpecificOperands.size() < minimumNumberOfSpecificOperands())
    {
      return wasSet;
    }

    ArrayList<SpecificOperand> oldSpecificOperands = new ArrayList<SpecificOperand>(specificOperands);
    specificOperands.clear();
    for (SpecificOperand aNewSpecificOperand : verifiedSpecificOperands)
    {
      specificOperands.add(aNewSpecificOperand);
      if (oldSpecificOperands.contains(aNewSpecificOperand))
      {
        oldSpecificOperands.remove(aNewSpecificOperand);
      }
      else
      {
        aNewSpecificOperand.addFragment(this);
      }
    }

    for (SpecificOperand anOldSpecificOperand : oldSpecificOperands)
    {
      anOldSpecificOperand.removeFragment(this);
    }
    wasSet = true;
    return wasSet;
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
  /* Code from template association_SetOneToMany */
  public boolean setSMSS(SMSS aSMSS)
  {
    boolean wasSet = false;
    if (aSMSS == null)
    {
      return wasSet;
    }

    SMSS existingSMSS = sMSS;
    sMSS = aSMSS;
    if (existingSMSS != null && !existingSMSS.equals(aSMSS))
    {
      existingSMSS.removeFragment(this);
    }
    sMSS.addFragment(this);
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
    Fragment existingFragment = aSpecificElement.getFragment();
    if (existingFragment == null)
    {
      aSpecificElement.setFragment(this);
    }
    else if (!this.equals(existingFragment))
    {
      existingFragment.removeSpecificElement(aSpecificElement);
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
      aSpecificElement.setFragment(null);
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

  public void delete()
  {
    ArrayList<SpecificOperand> copyOfSpecificOperands = new ArrayList<SpecificOperand>(specificOperands);
    specificOperands.clear();
    for(SpecificOperand aSpecificOperand : copyOfSpecificOperands)
    {
      aSpecificOperand.removeFragment(this);
    }
    SMSS placeholderSMSS = sMSS;
    this.sMSS = null;
    if(placeholderSMSS != null)
    {
      placeholderSMSS.removeFragment(this);
    }
    while( !specificElements.isEmpty() )
    {
      specificElements.get(0).setFragment(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sMSS = "+(getSMSS()!=null?Integer.toHexString(System.identityHashCode(getSMSS())):"null");
  }
}