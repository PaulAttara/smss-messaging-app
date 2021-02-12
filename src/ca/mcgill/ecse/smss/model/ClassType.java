/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;
import java.util.*;

// line 27 "../../../../../SMSS.ump"
public class ClassType
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, ClassType> classtypesByName = new HashMap<String, ClassType>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ClassType Attributes
  private String name;

  //ClassType Associations
  private SMSS sMSS;
  private Method method;
  private List<Object> objects;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ClassType(String aName, SMSS aSMSS)
  {
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddSMSS = setSMSS(aSMSS);
    if (!didAddSMSS)
    {
      throw new RuntimeException("Unable to create classType due to sMSS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    objects = new ArrayList<Object>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      classtypesByName.remove(anOldName);
    }
    classtypesByName.put(aName, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static ClassType getWithName(String aName)
  {
    return classtypesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }
  /* Code from template association_GetOne */
  public SMSS getSMSS()
  {
    return sMSS;
  }
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
  public Object getObject(int index)
  {
    Object aObject = objects.get(index);
    return aObject;
  }

  public List<Object> getObjects()
  {
    List<Object> newObjects = Collections.unmodifiableList(objects);
    return newObjects;
  }

  public int numberOfObjects()
  {
    int number = objects.size();
    return number;
  }

  public boolean hasObjects()
  {
    boolean has = objects.size() > 0;
    return has;
  }

  public int indexOfObject(Object aObject)
  {
    int index = objects.indexOf(aObject);
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
      existingSMSS.removeClassType(this);
    }
    sMSS.addClassType(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setMethod(Method aNewMethod)
  {
    boolean wasSet = false;
    if (method != null && !method.equals(aNewMethod) && equals(method.getClassType()))
    {
      //Unable to setMethod, as existing method would become an orphan
      return wasSet;
    }

    method = aNewMethod;
    ClassType anOldClassType = aNewMethod != null ? aNewMethod.getClassType() : null;

    if (!this.equals(anOldClassType))
    {
      if (anOldClassType != null)
      {
        anOldClassType.method = null;
      }
      if (method != null)
      {
        method.setClassType(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfObjects()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addObject(Object aObject)
  {
    boolean wasAdded = false;
    if (objects.contains(aObject)) { return false; }
    ClassType existingClassType = aObject.getClassType();
    boolean isNewClassType = existingClassType != null && !this.equals(existingClassType);
    if (isNewClassType)
    {
      aObject.setClassType(this);
    }
    else
    {
      objects.add(aObject);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeObject(Object aObject)
  {
    boolean wasRemoved = false;
    //Unable to remove aObject, as it must always have a classType
    if (!this.equals(aObject.getClassType()))
    {
      objects.remove(aObject);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addObjectAt(Object aObject, int index)
  {  
    boolean wasAdded = false;
    if(addObject(aObject))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfObjects()) { index = numberOfObjects() - 1; }
      objects.remove(aObject);
      objects.add(index, aObject);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveObjectAt(Object aObject, int index)
  {
    boolean wasAdded = false;
    if(objects.contains(aObject))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfObjects()) { index = numberOfObjects() - 1; }
      objects.remove(aObject);
      objects.add(index, aObject);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addObjectAt(aObject, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    classtypesByName.remove(getName());
    SMSS placeholderSMSS = sMSS;
    this.sMSS = null;
    if(placeholderSMSS != null)
    {
      placeholderSMSS.removeClassType(this);
    }
    Method existingMethod = method;
    method = null;
    if (existingMethod != null)
    {
      existingMethod.delete();
    }
    for(int i=objects.size(); i > 0; i--)
    {
      Object aObject = objects.get(i - 1);
      aObject.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sMSS = "+(getSMSS()!=null?Integer.toHexString(System.identityHashCode(getSMSS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "method = "+(getMethod()!=null?Integer.toHexString(System.identityHashCode(getMethod())):"null");
  }
}