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
  private List<SpecificMessage> specificMessages;

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
    specificMessages = new ArrayList<SpecificMessage>();
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
  public static int minimumNumberOfSpecificMessages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificMessage addSpecificMessage()
  {
    return new SpecificMessage(this);
  }

  public boolean addSpecificMessage(SpecificMessage aSpecificMessage)
  {
    boolean wasAdded = false;
    if (specificMessages.contains(aSpecificMessage)) { return false; }
    Operand existingOperand = aSpecificMessage.getOperand();
    boolean isNewOperand = existingOperand != null && !this.equals(existingOperand);
    if (isNewOperand)
    {
      aSpecificMessage.setOperand(this);
    }
    else
    {
      specificMessages.add(aSpecificMessage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificMessage(SpecificMessage aSpecificMessage)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificMessage, as it must always have a operand
    if (!this.equals(aSpecificMessage.getOperand()))
    {
      specificMessages.remove(aSpecificMessage);
      wasRemoved = true;
    }
    return wasRemoved;
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

  public void delete()
  {
    SMSS placeholderSMSS = sMSS;
    this.sMSS = null;
    if(placeholderSMSS != null)
    {
      placeholderSMSS.removeOperand(this);
    }
    for(int i=specificMessages.size(); i > 0; i--)
    {
      SpecificMessage aSpecificMessage = specificMessages.get(i - 1);
      aSpecificMessage.delete();
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