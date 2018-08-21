package org.galaxy.math;

public class Matrix {
	
	private int[][] data;
	private int rows;
	private int columns;
	
	public Matrix(int rows, int columns) {
		this.data = new int[rows][columns];
		this.rows = rows;
		this.columns = columns;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.data[i][j] = (int)(Math.random() * 7);
			}
		}
	}
	
	public int getValue(int row, int column) {
		return this.data[row][column];
	}
	
	public void setValue(int row, int column, int val) {
		this.data[row][column] = val;
	}
	
	//TODO: make sure same row, column
	public Matrix add(Matrix m) {
		Matrix newMatrix = new Matrix(this.rows, this.columns);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				newMatrix.data[i][j] = this.data[i][j] + m.data[i][j];
			}
		}
		return newMatrix;
	}
	
	//TODO: make sure this.columns = m.rows
	public Matrix multiple(Matrix m) {
		Matrix newMatrix = new Matrix(this.rows, m.columns);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < m.columns; j++) {
				int val = 0;
				for (int k = 0; k < this.rows; k++) {
					val += this.data[i][k] * m.data[k][j];
				}
				newMatrix.data[i][j] = val;
			}
		}
		return newMatrix;
	}
	
	public int getDeterminant() {
		if (this.rows == 1 && this.columns == 1) {
			return this.data[0][0];
		}
		int det = 0;
		int sign = 1;
		for (int j = 0; j < this.columns; j++) {
			Matrix sub = this.getSubMatrix(0, j);
			det += sign * this.data[0][j] * sub.getDeterminant();
			sign *= (-1);
		}
		return det;
	}
	
	public int getTrace() {
		int trace = 0;
		for (int i = 0; i < this.rows; i++) {
			trace += this.getValue(i, i);
		}
		return trace;
	}
	
	public boolean isSymmetrical() {
		if (this.rows != this.columns) {
			return false;
		}
		for (int i = 0; i < this.rows; i++) {
			for (int j = i + 1; j < this.columns; j++) {
				if (this.getValue(i, j) != this.getValue(j,  i)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Matrix getSubMatrix(int excludeRow, int excludeColumn) {
		Matrix subMatrix = new Matrix(this.rows - 1, this.columns - 1);
		int ii = 0;
		for (int i = 0; i < this.rows; i++) {
			if (i != excludeRow) {
				int jj = 0;
				for (int j = 0; j < this.columns; j++) {
					if (j != excludeColumn) {
						subMatrix.data[ii][jj] = this.data[i][j];
						jj++;
					}
				}
				ii++;
			}
		}
		return subMatrix;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < this.rows; i++) {
			buf.append("(");
			for (int j = 0; j < this.columns; j++) {
				buf.append(this.data[i][j]);
				if (j < this.columns - 1) {
					buf.append(" ");
				}
			}
			buf.append(")");
			buf.append("\n");
		}
		return buf.toString();
	}
}
