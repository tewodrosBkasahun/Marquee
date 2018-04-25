import java.awt.Color;
import cmscMarqueeLib.*;

/**
 * @author Tewodros Kasahun
 */
public class MarqueeDataManager implements DataManager {
	String message;
	int animationPattern;
	int stepCount = 0;
	Cell[][] shiftOverArray;

	public MarqueeDataManager(String message, int animationPattern) {
		this.message = message;
		this.animationPattern = animationPattern;
		Cell[][] toBePrinted = MessageToCell(message);
		shiftOverArray = generateEmptyCellsArray(ConfigValues.MARQUEE_HEIGHT, ConfigValues.MARQUEE_WIDTH);
		shiftOverArray = appendCell(shiftOverArray, toBePrinted);
		shiftOverArray = appendCell(shiftOverArray,
				generateEmptyCellsArray(ConfigValues.MARQUEE_HEIGHT, ConfigValues.MARQUEE_WIDTH));
	}

	@Override
	public Cell[][] step() {
		System.out.println(shiftOverArray[0].length);
		Cell[][] View = new Cell[ConfigValues.MARQUEE_HEIGHT][ConfigValues.MARQUEE_WIDTH];
		for (int i = 0; i < View.length; i++) {
			for (int j = 0; j < View[0].length; j++) {
				View[i][j] = shiftOverArray[i][j + stepCount];
			}
		}
		stepCount++;
		if (stepCount == shiftOverArray[0].length - ConfigValues.MARQUEE_WIDTH) {
			stepCount = 0;
		}
		return View;
	}

	public Cell[][] MessageToCell(String theMessage) {
		int[][] result = null;
		Cell[][] finalArray = null;
		Cell[][] cellResult = null;
		Cell[][] gap = new Cell[ConfigValues.CHARACTER_HEIGHT][1];

		for (int u = 0; u < gap.length; u++) {
			for (int t = 0; t < gap[0].length; t++) {
				gap[u][t] = new Cell(ConfigValues.BACKGROUND_COLOR);
			}
		}

		for (int x = 0; x < theMessage.length(); x++) {
			result = Alphabet.toIntArray(theMessage.charAt(x));
			cellResult = new Cell[result.length][result[0].length];
			for (int i = 0; i < result.length; i++) {
				// cellResult[i] = new Cell[][result[i].length];

				for (int j = 0; j < result[0].length; j++) {

					if (result[i][j] == 1) {
						cellResult[i][j] = new Cell(ConfigValues.FOREGROUND_COLOR);
					} else {
						cellResult[i][j] = new Cell(ConfigValues.BACKGROUND_COLOR);
					}
				}
			}
			if (x == 0) {
				finalArray = cellResult;
			} else {

				finalArray = appendCell(finalArray, cellResult);
			}
			if (x != theMessage.length() - 1) {
				finalArray = appendCell(finalArray, gap);
			}

		}
		return finalArray;
	}

	public Cell[][] appendCell(Cell[][] pre, Cell[][] theNew) {
		Cell[][] result = new Cell[pre.length][pre[0].length + theNew[0].length];
		for (int x = 0; x < pre.length; x++) {
			for (int y = 0; y < pre[x].length; y++) {
				result[x][y] = pre[x][y];
			}
		}
		for (int x = 0; x < pre.length; x++) {
			for (int y = 0; y < theNew[0].length; y++) {
				result[x][y + pre[0].length] = theNew[x][y];
			}
		}
		return result;
	}

	public Cell[][] generateEmptyCellsArray(int h, int w) {
		Cell[][] emptyPainted = new Cell[h][w];
		for (int x = 0; x < emptyPainted.length; x++) {
			for (int y = 0; y < emptyPainted[0].length; y++) {
				emptyPainted[x][y] = new Cell(ConfigValues.BACKGROUND_COLOR);
			}
		}
		return emptyPainted;
	}

}
