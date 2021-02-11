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
  private List<Method> methods;
  private List<ClassType> classTypes;
  private SenderObject senderObject;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SMSS()
  {
    methods = new ArrayList<Method>();
    classTypes = new ArrayList<ClassType>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Method getMethod(int index)
  {
    Method aMethod = methods.get(index);
    return aMethod;
  }

  public List<Method> getMethods()
  {
    List<Method> newMethods = Collections.unmodifiableList(methods);
    return newMethods;
  }

  public int numberOfMethods()
  {
    int number = methods.size();
    return number;
  }

  public boolean hasMethods()
  {
    boolean has = methods.size() > 0;
    return has;
  }

  public int indexOfMethod(Method aMethod)
  {
    int index = methods.indexOf(aMethod);
    return index;
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
  /* Code from template association_GetOne */
  public SenderObject getSenderObject()
  {
    return senderObject;
  }

  public boolean hasSenderObject()
  {
    boolean has = senderObject != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMethods()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Method addMethod(String aName, ClassType aClassType)
  {
    return new Method(aName, aClassType, this);
  }

  public boolean addMethod(Method aMethod)
  {
    boolean wasAdded = false;
    if (methods.contains(aMethod)) { return false; }
    SMSS existingSMSS = aMethod.getSMSS();
    boolean isNewSMSS = existingSMSS != null && !this.equals(existingSMSS);
    if (isNewSMSS)
    {
      aMethod.setSMSS(this);
    }
    else
    {
      methods.add(aMethod);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMethod(Method aMethod)
  {
    boolean wasRemoved = false;
    //Unable to remove aMethod, as it must always have a sMSS
    if (!this.equals(aMethod.getSMSS()))
    {
      methods.remove(aMethod);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMethodAt(Method aMethod, int index)
  {  
    boolean wasAdded = false;
    if(addMethod(aMethod))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMethods()) { index = numberOfMethods() - 1; }
      methods.remove(aMethod);
      methods.add(index, aMethod);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMethodAt(Method aMethod, int index)
  {
    boolean wasAdded = false;
    if(methods.contains(aMethod))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMethods()) { index = numberOfMethods() - 1; }
      methods.remove(aMethod);
      methods.add(index, aMethod);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMethodAt(aMethod, index);
    }
    return wasAdded;
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setSenderObject(SenderObject aNewSenderObject)
  {
    boolean wasSet = false;
    if (senderObject != null && !senderObject.equals(aNewSenderObject) && equals(senderObject.getSMSS()))
    {
      //Unable to setSenderObject, as existing senderObject would become an orphan
      return wasSet;
    }

    senderObject = aNewSenderObject;
    SMSS anOldSMSS = aNewSenderObject != null ? aNewSenderObject.getSMSS() : null;

    if (!this.equals(anOldSMSS))
    {
      if (anOldSMSS != null)
      {
        anOldSMSS.senderObject = null;
      }
      if (senderObject != null)
      {
        senderObject.setSMSS(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (methods.size() > 0)
    {
      Method aMethod = methods.get(methods.size() - 1);
      aMethod.delete();
      methods.remove(aMethod);
    }
    
    while (classTypes.size() > 0)
    {
      ClassType aClassType = classTypes.get(classTypes.size() - 1);
      aClassType.delete();
      classTypes.remove(aClassType);
    }
    
    SenderObject existingSenderObject = senderObject;
    senderObject = null;
    if (existingSenderObject != null)
    {
      existingSenderObject.delete();
      existingSenderObject.setSMSS(null);
    }
  }

}