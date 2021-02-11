/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 60 "../../../../../SSMS.ump"
public abstract class Fragment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Fragment Associations
  private List<Operand> operands;
  private Element element;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Fragment(Element aElement, Operand... allOperands)
  {
    operands = new ArrayList<Operand>();
    boolean didAddOperands = setOperands(allOperands);
    if (!didAddOperands)
    {
      throw new RuntimeException("Unable to create Fragment, must have at least 2 operands. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddElement = setElement(aElement);
    if (!didAddElement)
    {
      throw new RuntimeException("Unable to create fragment due to element. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Operand getOperand(int index)
  {
    Operand aOperand = operands.get(index);
    return aOperand;
  }

  public List<Operand> getOperands()
  {
    List<Operand> newOperands = Collections.unmodifiableList(operands);
    return newOperands;
  }

  public int numberOfOperands()
  {
    int number = operands.size();
    return number;
  }

  public boolean hasOperands()
  {
    boolean has = operands.size() > 0;
    return has;
  }

  public int indexOfOperand(Operand aOperand)
  {
    int index = operands.indexOf(aOperand);
    return index;
  }
  /* Code from template association_GetOne */
  public Element getElement()
  {
    return element;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfOperandsValid()
  {
    boolean isValid = numberOfOperands() >= minimumNumberOfOperands();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOperands()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addOperand(Operand aOperand)
  {
    boolean wasAdded = false;
    if (operands.contains(aOperand)) { return false; }
    operands.add(aOperand);
    if (aOperand.indexOfFragment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOperand.addFragment(this);
      if (!wasAdded)
      {
        operands.remove(aOperand);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeOperand(Operand aOperand)
  {
    boolean wasRemoved = false;
    if (!operands.contains(aOperand))
    {
      return wasRemoved;
    }

    if (numberOfOperands() <= minimumNumberOfOperands())
    {
      return wasRemoved;
    }

    int oldIndex = operands.indexOf(aOperand);
    operands.remove(oldIndex);
    if (aOperand.indexOfFragment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOperand.removeFragment(this);
      if (!wasRemoved)
      {
        operands.add(oldIndex,aOperand);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setOperands(Operand... newOperands)
  {
    boolean wasSet = false;
    ArrayList<Operand> verifiedOperands = new ArrayList<Operand>();
    for (Operand aOperand : newOperands)
    {
      if (verifiedOperands.contains(aOperand))
      {
        continue;
      }
      verifiedOperands.add(aOperand);
    }

    if (verifiedOperands.size() != newOperands.length || verifiedOperands.size() < minimumNumberOfOperands())
    {
      return wasSet;
    }

    ArrayList<Operand> oldOperands = new ArrayList<Operand>(operands);
    operands.clear();
    for (Operand aNewOperand : verifiedOperands)
    {
      operands.add(aNewOperand);
      if (oldOperands.contains(aNewOperand))
      {
        oldOperands.remove(aNewOperand);
      }
      else
      {
        aNewOperand.addFragment(this);
      }
    }

    for (Operand anOldOperand : oldOperands)
    {
      anOldOperand.removeFragment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOperandAt(Operand aOperand, int index)
  {  
    boolean wasAdded = false;
    if(addOperand(aOperand))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOperands()) { index = numberOfOperands() - 1; }
      operands.remove(aOperand);
      operands.add(index, aOperand);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOperandAt(Operand aOperand, int index)
  {
    boolean wasAdded = false;
    if(operands.contains(aOperand))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOperands()) { index = numberOfOperands() - 1; }
      operands.remove(aOperand);
      operands.add(index, aOperand);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOperandAt(aOperand, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setElement(Element aElement)
  {
    boolean wasSet = false;
    if (aElement == null)
    {
      return wasSet;
    }

    Element existingElement = element;
    element = aElement;
    if (existingElement != null && !existingElement.equals(aElement))
    {
      existingElement.removeFragment(this);
    }
    element.addFragment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (operands.size() > 0)
    {
      Operand aOperand = operands.get(operands.size() - 1);
      aOperand.delete();
      operands.remove(aOperand);
    }
    
    Element placeholderElement = element;
    this.element = null;
    if(placeholderElement != null)
    {
      placeholderElement.removeFragment(this);
    }
  }

}