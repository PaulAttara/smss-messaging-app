/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 63 "../../../../../SMSS.ump"
public class Operand
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Operand Attributes
  private String condition;

  //Autounique Attributes
  private int id;

  //Operand Associations
  private SMSS sMSS;
  private List<SpecificOperand> specificOperands;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Operand(String aCondition, SMSS aSMSS)
  {
    condition = aCondition;
    id = nextId++;
    boolean didAddSMSS = setSMSS(aSMSS);
    if (!didAddSMSS)
    {
      throw new RuntimeException("Unable to create operand due to sMSS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    specificOperands = new ArrayList<SpecificOperand>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCondition(String aCondition)
  {
    boolean wasSet = false;
    condition = aCondition;
    wasSet = true;
    return wasSet;
  }

  public String getCondition()
  {
    return condition;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public SMSS getSMSS()
  {
    return sMSS;
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
      existingSMSS.removeOperand(this);
    }
    sMSS.addOperand(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificOperands()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificOperand addSpecificOperand(Message... allMessages)
  {
    return new SpecificOperand(this, allMessages);
  }

  public boolean addSpecificOperand(SpecificOperand aSpecificOperand)
  {
    boolean wasAdded = false;
    if (specificOperands.contains(aSpecificOperand)) { return false; }
    Operand existingOperand = aSpecificOperand.getOperand();
    boolean isNewOperand = existingOperand != null && !this.equals(existingOperand);
    if (isNewOperand)
    {
      aSpecificOperand.setOperand(this);
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
    //Unable to remove aSpecificOperand, as it must always have a operand
    if (!this.equals(aSpecificOperand.getOperand()))
    {
      specificOperands.remove(aSpecificOperand);
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
    SMSS placeholderSMSS = sMSS;
    this.sMSS = null;
    if(placeholderSMSS != null)
    {
      placeholderSMSS.removeOperand(this);
    }
    for(int i=specificOperands.size(); i > 0; i--)
    {
      SpecificOperand aSpecificOperand = specificOperands.get(i - 1);
      aSpecificOperand.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "condition" + ":" + getCondition()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sMSS = "+(getSMSS()!=null?Integer.toHexString(System.identityHashCode(getSMSS())):"null");
  }
}