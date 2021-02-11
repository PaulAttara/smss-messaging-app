/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;

// line 17 "../../../../../SMSS.ump"
public class Method
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Method Attributes
  private String name;

  //Method Associations
  private ClassType classType;
  private SMSS sMSS;
  private Element element;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Method(String aName, ClassType aClassType, SMSS aSMSS)
  {
    name = aName;
    boolean didAddClassType = setClassType(aClassType);
    if (!didAddClassType)
    {
      throw new RuntimeException("Unable to create method due to classType. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSMSS = setSMSS(aSMSS);
    if (!didAddSMSS)
    {
      throw new RuntimeException("Unable to create method due to sMSS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne */
  public ClassType getClassType()
  {
    return classType;
  }
  /* Code from template association_GetOne */
  public SMSS getSMSS()
  {
    return sMSS;
  }
  /* Code from template association_GetOne */
  public Element getElement()
  {
    return element;
  }

  public boolean hasElement()
  {
    boolean has = element != null;
    return has;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setClassType(ClassType aNewClassType)
  {
    boolean wasSet = false;
    if (aNewClassType == null)
    {
      //Unable to setClassType to null, as method must always be associated to a classType
      return wasSet;
    }
    
    Method existingMethod = aNewClassType.getMethod();
    if (existingMethod != null && !equals(existingMethod))
    {
      //Unable to setClassType, the current classType already has a method, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    ClassType anOldClassType = classType;
    classType = aNewClassType;
    classType.setMethod(this);

    if (anOldClassType != null)
    {
      anOldClassType.setMethod(null);
    }
    wasSet = true;
    return wasSet;
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
      existingSMSS.removeMethod(this);
    }
    sMSS.addMethod(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setElement(Element aNewElement)
  {
    boolean wasSet = false;
    if (element != null && !element.equals(aNewElement) && equals(element.getMethod()))
    {
      //Unable to setElement, as existing element would become an orphan
      return wasSet;
    }

    element = aNewElement;
    Method anOldMethod = aNewElement != null ? aNewElement.getMethod() : null;

    if (!this.equals(anOldMethod))
    {
      if (anOldMethod != null)
      {
        anOldMethod.element = null;
      }
      if (element != null)
      {
        element.setMethod(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ClassType existingClassType = classType;
    classType = null;
    if (existingClassType != null)
    {
      existingClassType.setMethod(null);
    }
    SMSS placeholderSMSS = sMSS;
    this.sMSS = null;
    if(placeholderSMSS != null)
    {
      placeholderSMSS.removeMethod(this);
    }
    Element existingElement = element;
    element = null;
    if (existingElement != null)
    {
      existingElement.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "classType = "+(getClassType()!=null?Integer.toHexString(System.identityHashCode(getClassType())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "sMSS = "+(getSMSS()!=null?Integer.toHexString(System.identityHashCode(getSMSS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "element = "+(getElement()!=null?Integer.toHexString(System.identityHashCode(getElement())):"null");
  }
}