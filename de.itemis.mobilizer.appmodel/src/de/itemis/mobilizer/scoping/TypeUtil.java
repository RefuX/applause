package de.itemis.mobilizer.scoping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import de.itemis.mobilizer.appModelDsl.AppModelDslFactory;
import de.itemis.mobilizer.appModelDsl.CollectionIterator;
import de.itemis.mobilizer.appModelDsl.CollectionLiteral;
import de.itemis.mobilizer.appModelDsl.ComplexProviderConstruction;
import de.itemis.mobilizer.appModelDsl.ContentProvider;
import de.itemis.mobilizer.appModelDsl.Expression;
import de.itemis.mobilizer.appModelDsl.ObjectReference;
import de.itemis.mobilizer.appModelDsl.Parameter;
import de.itemis.mobilizer.appModelDsl.Property;
import de.itemis.mobilizer.appModelDsl.ProviderConstruction;
import de.itemis.mobilizer.appModelDsl.ScalarExpression;
import de.itemis.mobilizer.appModelDsl.SimpleProviderConstruction;
import de.itemis.mobilizer.appModelDsl.SimpleType;
import de.itemis.mobilizer.appModelDsl.StringConcat;
import de.itemis.mobilizer.appModelDsl.StringFunction;
import de.itemis.mobilizer.appModelDsl.StringLiteral;
import de.itemis.mobilizer.appModelDsl.StringReplace;
import de.itemis.mobilizer.appModelDsl.StringSplit;
import de.itemis.mobilizer.appModelDsl.StringUrlConform;
import de.itemis.mobilizer.appModelDsl.Type;
import de.itemis.mobilizer.appModelDsl.TypeDescription;
import de.itemis.mobilizer.appModelDsl.VariableDeclaration;
import de.itemis.mobilizer.appModelDsl.util.AppModelDslSwitch;

public class TypeUtil {
	
//	public class TypeDesc {
//		private final Type type;
//		private final boolean many;
//		
//		public TypeDesc(Type type, boolean many) {
//			this.type = type;
//			this.many = many;
//		}
//		
//		public Type getType() {
//			return type;
//		}
//		public boolean isMany() {
//			return many;
//		}
//	}

	private static AppModelDslSwitch<TypeDescription> typeOf = new AppModelDslSwitch<TypeDescription>() {
		public TypeDescription caseProperty(Property object) {
			return object.getDescription();
		};
		
		public TypeDescription caseVariableDeclaration(VariableDeclaration object) {
			return null;
		};

		public TypeDescription caseParameter(Parameter object) {
			return object.getDescription();
		};

		public TypeDescription caseCollectionIterator(CollectionIterator object) {
			return createDesc(doGetTypeOf(object.getCollection()).getType(), false);
		};

		public TypeDescription caseObjectReference(ObjectReference object) {
			while (object.getTail() != null)
				object = object.getTail();

			return doGetTypeOf(object.getObject());
		};
		
		public Type getStringType(EObject object) {
			EObject model = EcoreUtil.getRootContainer(object);
			List<SimpleType> allSimpleTypes = EcoreUtil2.getAllContentsOfType(model, SimpleType.class);
			Predicate<SimpleType> stringTypePredicate = new Predicate<SimpleType>() {
				public boolean apply(SimpleType input) {
					return "String".equals(input.getName());
				}
			};

			try {
				return Iterables.getOnlyElement(Iterables.filter(allSimpleTypes, stringTypePredicate));
			} catch(NoSuchElementException ex) {
				return null;
			}
		}
		
		public TypeDescription caseStringLiteral(StringLiteral object) {
			return createStringDesc(object);
		}
		
		public TypeDescription caseStringFunction(StringFunction object) {
			return createStringDesc(object);
		};
		
		public TypeDescription caseStringSplit(StringSplit object) {
			return createDesc(getStringType(object), true);
		};
		
		private TypeDescription createStringDesc(EObject object) {
			return createDesc(getStringType(object), false);
		};

		private TypeDescription createDesc(Type type, boolean isMany) {
			TypeDescription result = AppModelDslFactory.eINSTANCE.createTypeDescription();
			result.setMany(isMany);
			result.setType(type);
			return result;
		};
		
		public TypeDescription caseCollectionLiteral(CollectionLiteral object) {
			return createDesc(doGetTypeOf(object.getItems().get(0)).getType(), true);
		};
		
		public TypeDescription caseComplexProviderConstruction(ComplexProviderConstruction object) {
			ContentProvider p = object.getProvider();
			if(p==null)
				return null;
			
			return createDesc(p.getType(), p.isMany());
		};
		
		public TypeDescription caseSimpleProviderConstruction(SimpleProviderConstruction object) {
			return doGetTypeOf(object.getExpression());
		};
		
		
	};
	
	private static TypeDescription doGetTypeOf(EObject object) {
//		System.out.println("doGetTypeOf: " + object.eClass().getName());
		TypeDescription result = typeOf.doSwitch(object);
		if(result == null) {
			typeOf.doSwitch(object);
		}
		return result;
	}
	
	public static TypeDescription getTypeOf(VariableDeclaration declaration) {
		return doGetTypeOf(declaration);
	}
	
	public static TypeDescription getTypeOf(Expression expression) {
		return doGetTypeOf(expression);
	}
	
	public static TypeDescription getTypeOf(ProviderConstruction construction) {
		return doGetTypeOf(construction);
	}
	
	public static AppModelDslSwitch<Iterable<ObjectReference>> referencesIn = new AppModelDslSwitch<Iterable<ObjectReference>>() {
		public Iterable<ObjectReference> caseScalarExpression(ScalarExpression object) {
			// TODO: find out better way such as removed Iterables.emptyIterable()
			return new ArrayList<ObjectReference>();
		};
		
		public Iterable<ObjectReference> caseObjectReference(ObjectReference object) {
			return Arrays.asList(object);
		};
		
		public Iterable<ObjectReference> caseStringConcat(StringConcat sc) {
			// TODO: find out better way such as removed Iterables.emptyIterable()
			Iterable<ObjectReference> result = new ArrayList<ObjectReference>();
			for(ScalarExpression e : sc.getValues()) {
				result = Iterables.concat(result, getReferencesIn(e));
			}
			return result;
		};
		
		public Iterable<ObjectReference> caseStringUrlConform(StringUrlConform object) {
			return getReferencesIn(object.getValue());
		};
		
		public Iterable<ObjectReference> caseStringReplace(StringReplace object) {
			return Iterables.concat(
					getReferencesIn(object.getValue()),
					getReferencesIn(object.getMatch()),
					getReferencesIn(object.getReplacement())
					);
		};
		
		
	};

	public static List<ObjectReference> getReferencesIn(ScalarExpression e) {
		return ImmutableList.copyOf(referencesIn.doSwitch(e));
	}
	
	

	public static boolean isAssignable(TypeDescription target,
			TypeDescription value) {
		if(target == null || value == null)
			return false;
		return isAssignable(target.getType(), value.getType()) && (target.isMany() == value.isMany());  
	}

	private static boolean isAssignable(Type target, Type value) {
		if(target == null || value == null)
			return false;
		
		// look at type hierarchy
		return target == value;
	}

	public static String asReadableString(TypeDescription desc) {
		if(desc==null || desc.getType() == null)
			return "unknown";

		return desc.getType().getName() + (desc.isMany()?"[]":"");
	}

}
