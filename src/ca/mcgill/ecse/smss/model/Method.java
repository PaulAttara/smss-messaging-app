/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

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
  private List<SpecificElement> specificElements;

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
    specificElements = new ArrayList<SpecificElement>();
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
  /* Code from template association_GetMany */
  public SpecificElement getSpecificElement(int index)
  {
    SpecificElement aSpecificElement = specificElements.get(index);
    return aSpecificElement;
  }

  public List<SpecificElement> getSpecificElements()
  {
    List<SpecificElement> newSpecificElements = Collections.unmodifiableList(specificElements);
    return newSpecificElements;
  }

  public int numberOfSpecificElements()
  {
    int number = specificElements.size();
    return number;
  }

  public boolean hasSpecificElements()
  {
    boolean has = specificElements.size() > 0;
    return has;
  }

  public int indexOfSpecificElement(SpecificElement aSpecificElement)
  {
    int index = specificElements.indexOf(aSpecificElement);
    return index;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificElements()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificElement addSpecificElement(int aId, Message aMessage)
  {
    return new SpecificElement(aId, aMessage, this);
  }

  public boolean addSpecificElement(SpecificElement aSpecificElement)
  {
    boolean wasAdded = false;
    if (specificElements.contains(aSpecificElement)) { return false; }
    Method existingMethod = aSpecificElement.getMethod();
    boolean isNewMethod = existingMethod != null && !this.equals(existingMethod);
    if (isNewMethod)
    {
      aSpecificElement.setMethod(this);
    }
    else
    {
      specificElements.add(aSpecificElement);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificElement(SpecificElement aSpecificElement)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificElement, as it must always have a method
    if (!this.equals(aSpecificElement.getMethod()))
    {
      specificElements.remove(aSpecificElement);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificElementAt(SpecificElement aSpecificElement, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificElement(aSpecificElement))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificElements()) { index = numberOfSpecificElements() - 1; }
      specificElements.remove(aSpecificElement);
      specificElements.add(index, aSpecificElement);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificElementAt(SpecificElement aSpecificElement, int index)
  {
    boolean wasAdded = false;
    if(specificElements.contains(aSpecificElement))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificElements()) { index = numberOfSpecificElements() - 1; }
      specificElements.remove(aSpecificElement);
      specificElements.add(index, aSpecificElement);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificElementAt(aSpecificElement, index);
    }
    return wasAdded;
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
    for(int i=specificElements.size(); i > 0; i--)
    {
      SpecificElement aSpecificElement = specificElements.get(i - 1);
      aSpecificElement.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "classType = "+(getClassType()!=null?Integer.toHexString(System.identityHashCode(getClassType())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "sMSS = "+(getSMSS()!=null?Integer.toHexString(System.identityHashCode(getSMSS())):"null");
  }
}