/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.ssms.model;
import java.util.*;

// line 22 "../../../../../SSMS.ump"
public class ClassType
{

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
    name = aName;
    if (aSMSS == null || aSMSS.getClassType() != null)
    {
      throw new RuntimeException("Unable to create ClassType due to aSMSS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    sMSS = aSMSS;
    objects = new ArrayList<Object>();
  }

  public ClassType(String aName, Method aMethodForSMSS)
  {
    name = aName;
    sMSS = new SMSS(aMethodForSMSS, this);
    objects = new ArrayList<Object>();
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
    SMSS existingSMSS = sMSS;
    sMSS = null;
    if (existingSMSS != null)
    {
      existingSMSS.delete();
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