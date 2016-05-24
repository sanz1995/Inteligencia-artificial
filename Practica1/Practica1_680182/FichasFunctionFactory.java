package Practica1;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;
//esta
/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public class FichasFunctionFactory {
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	private static class EPActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {
			Fichas board = (Fichas) state;

			Set<Action> actions = new LinkedHashSet<Action>();
			if (board.canMoveGap(Fichas.RIGHT1)) {
				actions.add(Fichas.RIGHT1);
			}
			if (board.canMoveGap(Fichas.RIGHT2)) {
				actions.add(Fichas.RIGHT2);
			}
			if (board.canMoveGap(Fichas.RIGHT3)) {
				actions.add(Fichas.RIGHT3);
			}
			if (board.canMoveGap(Fichas.LEFT1)) {
				actions.add(Fichas.LEFT1);
			}
			if (board.canMoveGap(Fichas.LEFT2)) {
				actions.add(Fichas.LEFT2);
			}
			if (board.canMoveGap(Fichas.LEFT3)) {
				actions.add(Fichas.LEFT3);
			}

			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			Fichas board = (Fichas) s;
			System.out.println(a);
			if (Fichas.RIGHT1.equals(a)
					&& board.canMoveGap(Fichas.RIGHT1)) {
				Fichas newBoard = new Fichas(board);
				newBoard.moveGap1Right();
				return newBoard;
			} else if (Fichas.RIGHT2.equals(a)
					&& board.canMoveGap(Fichas.RIGHT2)) {
				Fichas newBoard = new Fichas(board);
				newBoard.moveGap2Right();
				System.out.println(newBoard);
				return newBoard;
			} else if (Fichas.RIGHT3.equals(a)
					&& board.canMoveGap(Fichas.RIGHT3)) {
				Fichas newBoard = new Fichas(board);
				newBoard.moveGap3Right();
				return newBoard;
			} else if (Fichas.LEFT1.equals(a)
					&& board.canMoveGap(Fichas.LEFT1)) {
				Fichas newBoard = new Fichas(board);
				newBoard.moveGap1Left();
				return newBoard;
			}else if (Fichas.LEFT2.equals(a)
					&& board.canMoveGap(Fichas.LEFT2)) {
				Fichas newBoard = new Fichas(board);
				newBoard.moveGap2Left();
				return newBoard;
			}else if (Fichas.LEFT3.equals(a)
					&& board.canMoveGap(Fichas.LEFT3)) {
				Fichas newBoard = new Fichas(board);
				newBoard.moveGap3Left();
				return newBoard;
			}

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}