/*
Violet - A program for editing UML diagrams.

Copyright (C) 2002 Cay S. Horstmann (http://horstmann.com)

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package instanceOf.violet.callEdge.getPoints;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import com.horstmann.violet.ArrowHead;
import com.horstmann.violet.framework.Direction;

/**
   An edge that joins two call nodes.
 */
public class CallEdge extends SegmentedLineEdge
{
	public CallEdge()
	{
		setSignal(false);
	}

	/**
      Gets the signal property.
      @return true if this is a signal edge
	 */
	public boolean isSignal() { return signal; }

	/**
      Sets the signal property.
      @param newValue true if this is a signal edge
	 */      
	public void setSignal(boolean newValue) 
	{ 
		signal = newValue; 
		if (signal)
			setEndArrowHead(ArrowHead.HALF_V);
		else
			setEndArrowHead(ArrowHead.V);
	}

	public ArrayList<Point2D> getPoints()
	{
		Node endNode = getEnd();
		Node startNode = getStart();

		return endNode.getPoints(startNode);
	}

	private boolean signal;
}


