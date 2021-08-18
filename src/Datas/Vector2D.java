/***************************************************************************
 *   MIT License
 *   
 *   Copyright (c) 2021 Sadig Akhund
 *   
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *   
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *   
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 *
 * 
 **************************************************************************/
package Datas;

public class Vector2D<A, B> {

    /**
     * Value A
     */
    private A a;
    /**
     * Value B
     */
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

    /**************************************************************************************************
     * *************************************** SETTERS & GETTERS
     **************************************************************************************************/

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
