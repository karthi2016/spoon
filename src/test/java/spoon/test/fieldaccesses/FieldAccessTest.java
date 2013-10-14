package spoon.test.fieldaccesses;

import static org.junit.Assert.assertEquals;
import static spoon.test.TestUtils.build;

import org.junit.Test;

import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtSimpleType;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.reflect.visitor.filter.TypeFilter;

public class FieldAccessTest {

	@Test 
	public void testModelBuildingFieldAccesses() throws Exception {
		CtSimpleType type = build ("spoon.test.fieldaccesses",  "Mouse");
		assertEquals("Mouse", type.getSimpleName());

		CtMethod meth1 = (CtMethod) type.getElements(new NameFilter("meth1")).get(0);
		CtMethod meth1b = (CtMethod) type.getElements(new NameFilter("meth1b")).get(0);

		assertEquals(2, meth1.getElements(new TypeFilter(CtFieldAccess.class)).size());
		
		// here we seen that an explicit this counts as a field access
		// I'm not sure this is correct
		// we should add a ThisAccess and maybe super access
		assertEquals(3, meth1b.getElements(new TypeFilter(CtFieldAccess.class)).size());

		CtMethod meth2 = (CtMethod) type.getElements(new NameFilter("meth2")).get(0);
		assertEquals(3, meth2.getElements(new TypeFilter(CtFieldAccess.class)).size());
		
		CtMethod meth3 = (CtMethod) type.getElements(new NameFilter("meth3")).get(0);
		assertEquals(3, meth3.getElements(new TypeFilter(CtFieldAccess.class)).size());

		CtMethod meth4 = (CtMethod) type.getElements(new NameFilter("meth4")).get(0);
		assertEquals(1, meth4.getElements(new TypeFilter(CtFieldAccess.class)).size());

	}

}
