
public class Matrix {
	
	private double[][] data;
	private int rows;
	private int columns;
	
	public Matrix(int rows, int columns) {
		this.data = new double[rows][columns];
		this.rows = rows;
		this.columns = columns;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				data[i][j] = 0;
			}
		}
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < rows; i++) {
			buf.append("(");
			for (int j = 0; j < columns; j++) {
				buf.append(data[i][j]);
				if (j < columns - 1) {
					buf.append(" ");
				}
			}
			buf.append(")");
			buf.append("\n");
		}
		return buf.toString();
	}
}
