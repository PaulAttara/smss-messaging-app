/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.smss.model;

// line 13 "../../../../../SMSS.ump"
public class SpecificElement
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes
  private int id;

  //SpecificElement Associations
  private Fragment fragment;
  private Message message;
  private Method method;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificElement(Method aMethod)
  {
    id = nextId++;
    boolean didAddMethod = setMethod(aMethod);
    if (!didAddMethod)
    {
      throw new RuntimeException("Unable to create specificElement due to method. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public Fragment getFragment()
  {
    return fragment;
  }

  public boolean hasFragment()
  {
    boolean has = fragment != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Message getMessage()
  {
    return message;
  }

  public boolean hasMessage()
  {
    boolean has = message != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Method getMethod()
  {
    return method;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setFragment(Fragment aFragment)
  {
    boolean wasSet = false;
    Fragment existingFragment = fragment;
    fragment = aFragment;
    if (existingFragment != null && !existingFragment.equals(aFragment))
    {
      existingFragment.removeSpecificElement(this);
    }
    if (aFragment != null)
    {
      aFragment.addSpecificElement(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setMessage(Message aMessage)
  {
    boolean wasSet = false;
    Message existingMessage = message;
    message = aMessage;
    if (existingMessage != null && !existingMessage.equals(aMessage))
    {
      existingMessage.removeSpecificElement(this);
    }
    if (aMessage != null)
    {
      aMessage.addSpecificElement(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMethod(Method aMethod)
  {
    boolean wasSet = false;
    if (aMethod == null)
    {
      return wasSet;
    }

    Method existingMethod = method;
    method = aMethod;
    if (existingMethod != null && !existingMethod.equals(aMethod))
    {
      existingMethod.removeSpecificElement(this);
    }
    method.addSpecificElement(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (fragment != null)
    {
      Fragment placeholderFragment = fragment;
      this.fragment = null;
      placeholderFragment.removeSpecificElement(this);
    }
    if (message != null)
    {
      Message placeholderMessage = message;
      this.message = null;
      placeholderMessage.removeSpecificElement(this);
    }
    Method placeholderMethod = method;
    this.method = null;
    if(placeholderMethod != null)
    {
      placeholderMethod.removeSpecificElement(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "fragment = "+(getFragment()!=null?Integer.toHexString(System.identityHashCode(getFragment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "message = "+(getMessage()!=null?Integer.toHexString(System.identityHashCode(getMessage())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "method = "+(getMethod()!=null?Integer.toHexString(System.identityHashCode(getMethod())):"null");
  }
}