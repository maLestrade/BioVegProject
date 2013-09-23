package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;

public class OnlyVerticalScrollPanel extends JPanel implements Scrollable
{
	private static final long serialVersionUID = 1L;

	public OnlyVerticalScrollPanel()
    {
        this(new GridLayout(0, 1));
    }

    public OnlyVerticalScrollPanel(LayoutManager lm)
    {
        super(lm);
    }

    public OnlyVerticalScrollPanel(Component comp)
    {
        this();
        add(comp);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize()
    {
        return(getPreferredSize());
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect,
            int orientation, int direction)
    {
        return(10);
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect,
            int orientation, int direction)
    {
        return(100);
    }

    @Override
    public boolean getScrollableTracksViewportWidth()
    {
        return(true);
    }

    @Override
    public boolean getScrollableTracksViewportHeight()
    {
        return(false);
    }
}
