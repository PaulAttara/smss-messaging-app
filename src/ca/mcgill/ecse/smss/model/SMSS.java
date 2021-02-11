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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SMSS()
  {
    classTypes = new ArrayList<ClassType>();
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
    
  }

}