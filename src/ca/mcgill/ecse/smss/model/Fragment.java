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
  private SpecificElement specificElement;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Fragment(SMSS aSMSS, SpecificElement aSpecificElement, SpecificOperand... allSpecificOperands)
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
    boolean didAddSpecificElement = setSpecificElement(aSpecificElement);
    if (!didAddSpecificElement)
    {
      throw new RuntimeException("Unable to create fragment due to specificElement. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  /* Code from template association_GetOne */
  public SpecificElement getSpecificElement()
  {
    return specificElement;
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
    while (specificOperands.size() > 0)
    {
      SpecificOperand aSpecificOperand = specificOperands.get(specificOperands.size() - 1);
      aSpecificOperand.delete();
      specificOperands.remove(aSpecificOperand);
    }
    
    SMSS placeholderSMSS = sMSS;
    this.sMSS = null;
    if(placeholderSMSS != null)
    {
      placeholderSMSS.removeFragment(this);
    }
    SpecificElement existingSpecificElement = specificElement;
    specificElement = null;
    if (existingSpecificElement != null)
    {
      existingSpecificElement.setFragment(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sMSS = "+(getSMSS()!=null?Integer.toHexString(System.identityHashCode(getSMSS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "specificElement = "+(getSpecificElement()!=null?Integer.toHexString(System.identityHashCode(getSpecificElement())):"null");
  }
}