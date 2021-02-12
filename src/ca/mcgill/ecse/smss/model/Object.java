/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;

// line 32 "../../../../../SMSS.ump"
public abstract class Object
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Object Associations
  private ClassType classType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Object(ClassType aClassType)
  {
    boolean didAddClassType = setClassType(aClassType);
    if (!didAddClassType)
    {
      throw new RuntimeException("Unable to create object due to classType. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public ClassType getClassType()
  {
    return classType;
  }
  /* Code from template association_SetOneToMany */
  public boolean setClassType(ClassType aClassType)
  {
    boolean wasSet = false;
    if (aClassType == null)
    {
      return wasSet;
    }

    ClassType existingClassType = classType;
    classType = aClassType;
    if (existingClassType != null && !existingClassType.equals(aClassType))
    {
      existingClassType.removeObject(this);
    }
    classType.addObject(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ClassType placeholderClassType = classType;
    this.classType = null;
    if(placeholderClassType != null)
    {
      placeholderClassType.removeObject(this);
    }
  }

}