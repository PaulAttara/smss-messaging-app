/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;

// line 58 "../../../../../SMSS.ump"
public class Condition
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Condition Associations
  private Operand operand;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Condition(Operand aOperand)
  {
    boolean didAddOperand = setOperand(aOperand);
    if (!didAddOperand)
    {
      throw new RuntimeException("Unable to create condition due to operand. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Operand getOperand()
  {
    return operand;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setOperand(Operand aNewOperand)
  {
    boolean wasSet = false;
    if (aNewOperand == null)
    {
      //Unable to setOperand to null, as condition must always be associated to a operand
      return wasSet;
    }
    
    Condition existingCondition = aNewOperand.getCondition();
    if (existingCondition != null && !equals(existingCondition))
    {
      //Unable to setOperand, the current operand already has a condition, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Operand anOldOperand = operand;
    operand = aNewOperand;
    operand.setCondition(this);

    if (anOldOperand != null)
    {
      anOldOperand.setCondition(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Operand existingOperand = operand;
    operand = null;
    if (existingOperand != null)
    {
      existingOperand.setCondition(null);
    }
  }

}