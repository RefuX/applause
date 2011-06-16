/**
 * <copyright>
 * </copyright>
 *
 */
package de.itemis.mobilizer.appModelDsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tabbar Button</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.itemis.mobilizer.appModelDsl.TabbarButton#getTitle <em>Title</em>}</li>
 *   <li>{@link de.itemis.mobilizer.appModelDsl.TabbarButton#getIcon <em>Icon</em>}</li>
 *   <li>{@link de.itemis.mobilizer.appModelDsl.TabbarButton#getView <em>View</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.itemis.mobilizer.appModelDsl.AppModelDslPackage#getTabbarButton()
 * @model
 * @generated
 */
public interface TabbarButton extends EObject
{
  /**
   * Returns the value of the '<em><b>Title</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Title</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Title</em>' containment reference.
   * @see #setTitle(ScalarExpression)
   * @see de.itemis.mobilizer.appModelDsl.AppModelDslPackage#getTabbarButton_Title()
   * @model containment="true"
   * @generated
   */
  ScalarExpression getTitle();

  /**
   * Sets the value of the '{@link de.itemis.mobilizer.appModelDsl.TabbarButton#getTitle <em>Title</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Title</em>' containment reference.
   * @see #getTitle()
   * @generated
   */
  void setTitle(ScalarExpression value);

  /**
   * Returns the value of the '<em><b>Icon</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Icon</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Icon</em>' containment reference.
   * @see #setIcon(ScalarExpression)
   * @see de.itemis.mobilizer.appModelDsl.AppModelDslPackage#getTabbarButton_Icon()
   * @model containment="true"
   * @generated
   */
  ScalarExpression getIcon();

  /**
   * Sets the value of the '{@link de.itemis.mobilizer.appModelDsl.TabbarButton#getIcon <em>Icon</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Icon</em>' containment reference.
   * @see #getIcon()
   * @generated
   */
  void setIcon(ScalarExpression value);

  /**
   * Returns the value of the '<em><b>View</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>View</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>View</em>' containment reference.
   * @see #setView(ViewCall)
   * @see de.itemis.mobilizer.appModelDsl.AppModelDslPackage#getTabbarButton_View()
   * @model containment="true"
   * @generated
   */
  ViewCall getView();

  /**
   * Sets the value of the '{@link de.itemis.mobilizer.appModelDsl.TabbarButton#getView <em>View</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>View</em>' containment reference.
   * @see #getView()
   * @generated
   */
  void setView(ViewCall value);

} // TabbarButton
