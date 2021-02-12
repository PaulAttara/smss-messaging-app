/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 3 "../../../../../SMSS.ump"
public class SMSS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SMSS Associations
  private Method method;
  private List<ClassType> classTypes;
  private List<Fragment> fragments;
  private List<Operand> operands;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SMSS()
  {
    classTypes = new ArrayList<ClassType>();
    fragments = new ArrayList<Fragment>();
    operands = new ArrayList<Operand>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Method getMethod()
  {
    return method;
  }

  public boolean hasMethod()
  {
    boolean has = method != null;
    return has;
  }
  /* Code from template association_GetMany */
  public ClassType getClassType(int index)
  {
    ClassType aClassType = classTypes.get(index);
    return aClassType;
  }

  public List<ClassType> getClassTypes()
  {
    List<ClassType> newClassTypes = Collections.unmodifiableList(classTypes);
    return newClassTypes;
  }

  public int numberOfClassTypes()
  {
    int number = classTypes.size();
    return number;
  }

  public boolean hasClassTypes()
  {
    boolean has = classTypes.size() > 0;
    return has;
  }

  public int indexOfClassType(ClassType aClassType)
  {
    int index = classTypes.indexOf(aClassType);
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
  /* Code from template association_GetMany */
  public Operand getOperand(int index)
  {
    Operand aOperand = operands.get(index);
    return aOperand;
  }

  /**
   * added this line!!!!
   */
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setMethod(Method aNewMethod)
  {
    boolean wasSet = false;
    if (method != null && !method.equals(aNewMethod) && equals(method.getSMSS()))
    {
      //Unable to setMethod, as existing method would become an orphan
      return wasSet;
    }

    method = aNewMethod;
    SMSS anOldSMSS = aNewMethod != null ? aNewMethod.getSMSS() : null;

    if (!this.equals(anOldSMSS))
    {
      if (anOldSMSS != null)
      {
        anOldSMSS.method = null;
      }
      if (method != null)
      {
        method.setSMSS(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClassTypes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ClassType addClassType(String aName)
  {
    return new ClassType(aName, this);
  }

  public boolean addClassType(ClassType aClassType)
  {
    boolean wasAdded = false;
    if (classTypes.contains(aClassType)) { return false; }
    SMSS existingSMSS = aClassType.getSMSS();
    boolean isNewSMSS = existingSMSS != null && !this.equals(existingSMSS);
    if (isNewSMSS)
    {
      aClassType.setSMSS(this);
    }
    else
    {
      classTypes.add(aClassType);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClassType(ClassType aClassType)
  {
    boolean wasRemoved = false;
    //Unable to remove aClassType, as it must always have a sMSS
    if (!this.equals(aClassType.getSMSS()))
    {
      classTypes.remove(aClassType);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClassTypeAt(ClassType aClassType, int index)
  {  
    boolean wasAdded = false;
    if(addClassType(aClassType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClassTypes()) { index = numberOfClassTypes() - 1; }
      classTypes.remove(aClassType);
      classTypes.add(index, aClassType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClassTypeAt(ClassType aClassType, int index)
  {
    boolean wasAdded = false;
    if(classTypes.contains(aClassType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClassTypes()) { index = numberOfClassTypes() - 1; }
      classTypes.remove(aClassType);
      classTypes.add(index, aClassType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClassTypeAt(aClassType, index);
    }
    return wasAdded;
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
    SMSS existingSMSS = aFragment.getSMSS();
    boolean isNewSMSS = existingSMSS != null && !this.equals(existingSMSS);
    if (isNewSMSS)
    {
      aFragment.setSMSS(this);
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
    //Unable to remove aFragment, as it must always have a sMSS
    if (!this.equals(aFragment.getSMSS()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOperands()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Operand addOperand(String aCondition)
  {
    return new Operand(aCondition, this);
  }

  public boolean addOperand(Operand aOperand)
  {
    boolean wasAdded = false;
    if (operands.contains(aOperand)) { return false; }
    SMSS existingSMSS = aOperand.getSMSS();
    boolean isNewSMSS = existingSMSS != null && !this.equals(existingSMSS);
    if (isNewSMSS)
    {
      aOperand.setSMSS(this);
    }
    else
    {
      operands.add(aOperand);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOperand(Operand aOperand)
  {
    boolean wasRemoved = false;
    //Unable to remove aOperand, as it must always have a sMSS
    if (!this.equals(aOperand.getSMSS()))
    {
      operands.remove(aOperand);
      wasRemoved = true;
    }
    return wasRemoved;
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

  public void delete()
  {
    Method existingMethod = method;
    method = null;
    if (existingMethod != null)
    {
      existingMethod.delete();
      existingMethod.setSMSS(null);
    }
    while (classTypes.size() > 0)
    {
      ClassType aClassType = classTypes.get(classTypes.size() - 1);
      aClassType.delete();
      classTypes.remove(aClassType);
    }
    
    while (fragments.size() > 0)
    {
      Fragment aFragment = fragments.get(fragments.size() - 1);
      aFragment.delete();
      fragments.remove(aFragment);
    }
    
    while (operands.size() > 0)
    {
      Operand aOperand = operands.get(operands.size() - 1);
      aOperand.delete();
      operands.remove(aOperand);
    }
    
  }

}