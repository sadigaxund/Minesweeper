
public class Vector2D<A, B> {

    private A a;
    private B b;

    public Vector2D() {
    }

    public Vector2D(A a, B b) {
	setA(a);
	setB(b);
    }

    public Vector2D(Vector2D<A, B> vec) {
	this(vec.getA(), vec.getB());
    }

    /**
     * @return the a value
     */
    public A getA() {
	return a;
    }

    /**
     * @param a
     *              the a value to set
     */
    public void setA(A a) {
	this.a = a;
    }

    /**
     * @return the b value
     */
    public B getB() {
	return b;
    }

    /**
     * @param b
     *              the b value to set
     */
    public void setB(B b) {
	this.b = b;
    }

}
