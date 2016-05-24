package Practica1;


import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

/**
 * @author Ravi Mohan
 * @author R. Lunde
 */
public class Fichas {

	public static Action RIGHT1 = new DynamicAction("RIGHT1");

	public static Action RIGHT2 = new DynamicAction("RIGHT2");
	
	public static Action RIGHT3 = new DynamicAction("RIGHT3");

	public static Action LEFT1 = new DynamicAction("LEFT1");
	
	public static Action LEFT2 = new DynamicAction("LEFT2");
	
	public static Action LEFT3 = new DynamicAction("LEFT3");

	private int[] state;

	//
	// PUBLIC METHODS
	//

	public Fichas() {
		state = new int[] { 1, 1, 1, 0, 2, 2, 2 };
	}

	public Fichas(int[] state) {
		this.state = new int[state.length];
		System.arraycopy(state, 0, this.state, 0, state.length);
	}

	public Fichas(Fichas copyBoard) {
		this(copyBoard.getState());
	}

	public int[] getState() {
		return state;
	}

	public void moveGap1Right() {
		int gapPos = getGapPosition();
		if (gapPos < 6) {
			int valueOnRight = getValueAt(gapPos + 1);
			setValue(gapPos, valueOnRight);
			setValue(gapPos+1, 0);
		}

	}

	public void moveGap2Right() {
		int gapPos = getGapPosition();
		if (gapPos < 5) {
			int valueOnRight = getValueAt(gapPos + 2);
			setValue(gapPos, valueOnRight);
			setValue(gapPos+2, 0);
		}

	}
	
	public void moveGap3Right() {
		int gapPos = getGapPosition();
		if (gapPos < 4) {
			int valueOnRight = getValueAt(gapPos + 3);
			setValue(gapPos, valueOnRight);
			setValue(gapPos+3, 0);
		}

	}
	
	public void moveGap1Left() {
		int gapPos = getGapPosition();
		if (gapPos > 0) {
			int valueOnRight = getValueAt(gapPos - 1);
			setValue(gapPos, valueOnRight);
			setValue(gapPos-1, 0);
		}

	}
	
	
	public void moveGap2Left() {
		int gapPos = getGapPosition();
		if (gapPos > 1) {
			int valueOnRight = getValueAt(gapPos - 2);
			setValue(gapPos, valueOnRight);
			setValue(gapPos-2, 0);
		}

	}
	
	public void moveGap3Left() {
		int gapPos = getGapPosition();
		if (gapPos > 2) {
			int valueOnRight = getValueAt(gapPos - 3);
			setValue(gapPos, valueOnRight);
			setValue(gapPos-3, 0);
		}
	}

	public boolean canMoveGap(Action where) {
		boolean retVal = true;
		int pos = getGapPosition();
		if (where.equals(LEFT1))
			retVal = (pos > 0);
		else if (where.equals(LEFT2))
			retVal = (pos > 1);
		else if (where.equals(LEFT3))
			retVal = (pos > 2);
		else if (where.equals(RIGHT1))
			retVal = (pos < 6);
		else if (where.equals(RIGHT2))
			retVal = (pos < 5);
		else if (where.equals(RIGHT3))
			retVal = (pos < 4);
		return retVal;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		Fichas aBoard = (Fichas) o;

		for (int i = 0; i < 7; i++) {
			if(state[i]!=aBoard.getValueAt(i)){
				return false;
			}
		}
		return true;
	}
/**
	@Override
	public int hashCode() {
		int result = 17;
		for (int i = 0; i < 8; i++) {
			int position = this.getPositionOf(i);
			result = 37 * result + position;
		}
		return result;
	}
*/
	@Override
	public String toString() {
		String retVal = state[0] + " " + state[1] + " " + state[2] + "\n"
				+ state[3] + " " + state[4] + " " + state[5] + " " + "\n"
				+ state[6];
		return retVal;
	}

	//
	// PRIVATE METHODS
	//


	private int getValueAt(int posicion) {
		// refactor this use either case or a div/mod soln
		return state[posicion];
	}

	private int getGapPosition() {
		return getPositionOf(0);
	}

	private int getPositionOf(int val) {
		int retVal = -1;
		for (int i = 0; i < 7; i++) {
			if (state[i] == val) {
				retVal = i;
			}
		}
		return retVal;
	}
	private void setValue(int pos, int val) {
		state[pos] = val;

	}
}