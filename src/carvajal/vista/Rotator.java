package carvajal.vista;

 
 import java.awt.event.*;
 import javax.swing.*;
 
 import org.jfree.chart.plot.PiePlot3D;
 
class Rotator extends Timer implements ActionListener {

	/** The plot. */
	private PiePlot3D plot;
	
	/** The angle. */
	private int angle = 270;
	
	/**
	* Constructor.
	*
	* @param plot  the plot.
	*/
	Rotator(final PiePlot3D plot) {
		super(100, null);
		this.plot = plot;
		addActionListener(this);
	}
	
	/**
	* Modifies the starting angle.
	*
	* @param event  the action event.
	*/
	public void actionPerformed(final ActionEvent event) {
		this.plot.setStartAngle(this.angle);
		this.angle = this.angle + 1;
		if (this.angle == 360) {
			this.angle = 0;
		}
	}

}