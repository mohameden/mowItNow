package org.mowitnow.mower.application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.mowitnow.mower.model.Direction;
import org.mowitnow.mower.model.Lawn;
import org.mowitnow.mower.model.Mower;
import org.mowitnow.mower.model.Position;

public class Main {
	private static final String DIMENSIONS_REGEX = "^[0-9]+ [0-9]+";
	private static final String POSITION_REGEX = "^[0-9]+ [0-9]+ [NESW]$";
	private static final String COMMAND_REGEX = "^[ADG]+";

	public static void main(final String[] args) throws Exception {
		if (args == null || args.length != 2) {
			System.err.println("usage : MainClass input_file output_file");
		}

		final Scanner in = new Scanner(new FileInputStream(args[0]));

		final FileOutputStream output = new FileOutputStream(args[1]);
		String line = in.nextLine();
		if (line.matches(DIMENSIONS_REGEX)) {
			final String[] dimensions = line.split(" ");
			final int maxX = Integer.valueOf(dimensions[0]);
			final int maxY = Integer.valueOf(dimensions[1]);
			final Lawn lawn = new Lawn(maxX, maxY);
			while (in.hasNext()) {
				line = in.nextLine();
				if (line.matches(POSITION_REGEX)) {
					final String[] coordonates = line.split(" ");
					final int x = Integer.valueOf(coordonates[0]);
					final int y = Integer.valueOf(coordonates[1]);
					final char code = coordonates[2].charAt(0);
					final Position position = new Position(x, y,
							Direction.findByCode(code));
					line = in.nextLine();
					if (line.matches(COMMAND_REGEX)) {
						final Mower mower = new Mower(lawn, position);
						final String result = mower.executeCommand(line);
						writeLine(output, result);
					} else {
						writeLine(output, "Command to exec is not valid");
					}
				} else {
					writeLine(output, "Initial position is not valid");
				}
			}
		} else {
			writeLine(output, "Lawn dimensions are not valid");
		}
		in.close();
		output.close();
	}

	private static void writeLine(final FileOutputStream output,
			final String line) throws IOException {
		output.write(line.getBytes());
		output.write("\n".getBytes());
	}
}
