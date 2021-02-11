/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.ssms.model;

// line 3 "../../../../../SSMS.ump"
public class SMSS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SMSS Associations
  private Method method;
  private ClassType classType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SMSS(Method aMethod, ClassType aClassType)
  {
    if (aMethod == null || aMethod.getSMSS() != null)
    {
      throw new RuntimeException("Unable to create SMSS due to aMethod. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    method = aMethod;
    if (aClassType == null || aClassType.getSMSS() != null)
    {
      throw new RuntimeException("Unable to create SMSS due to aClassType. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    classType = aClassType;
  }

  public SMSS(String aNameForMethod, ClassType aClassTypeForMethod, String aNameForClassType)
  {
    method = new Method(aNameForMethod, aClassTypeForMethod, this);
    classType = new ClassType(aNameForClassType, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Method getMethod()
  {
    return method;
  }
  /* Code from template association_GetOne */
  public ClassType getClassType()
  {
    return classType;
  }

  public void delete()
  {
    Method existingMethod = method;
    method = null;
    if (existingMethod != null)
    {
      existingMethod.delete();
    }
    ClassType existingClassType = classType;
    classType = null;
    if (existingClassType != null)
    {
      existingClassType.delete();
    }
  }

}