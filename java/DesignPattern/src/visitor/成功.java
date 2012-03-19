package visitor;

/**
 * ConcreteVisitor
 * 
 * @author Anders Zhu
 * 
 */
public class 成功 extends Action {
	@Override
	public void getManConclusion(男人 man) {
		System.out.println("男人：成功");
	}

	@Override
	public void getWomanConclusion(女人 woman) {
		System.out.println("女人：成功");
	}
}
