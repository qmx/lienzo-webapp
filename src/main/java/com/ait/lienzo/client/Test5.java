package com.ait.lienzo.client;

import com.ait.lienzo.client.core.Context2D;
import com.ait.lienzo.client.core.event.NodeDragEndEvent;
import com.ait.lienzo.client.core.event.NodeDragEndHandler;
import com.ait.lienzo.client.core.event.NodeDragStartEvent;
import com.ait.lienzo.client.core.event.NodeDragStartHandler;
import com.ait.lienzo.client.core.event.NodeMouseClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseDownEvent;
import com.ait.lienzo.client.core.event.NodeMouseDownHandler;
import com.ait.lienzo.client.core.event.NodeMouseEnterEvent;
import com.ait.lienzo.client.core.event.NodeMouseEnterHandler;
import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.OrthogonalPolyLine;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.lienzo.client.core.shape.Star;
import com.ait.lienzo.client.core.shape.wires.AlignAndDistribute;
import com.ait.lienzo.client.core.shape.wires.Connector;
import com.ait.lienzo.client.core.shape.wires.DragAndDropManager;
import com.ait.lienzo.client.core.shape.wires.IControlHandle;
import com.ait.lienzo.client.core.shape.wires.IControlHandleList;
import com.ait.lienzo.client.core.shape.wires.Magnet;
import com.ait.lienzo.client.core.shape.wires.IMagnets;
import com.ait.lienzo.client.core.shape.wires.MagnetManager;
import com.ait.lienzo.client.core.shape.wires.WiresLayer;
import com.ait.lienzo.client.core.shape.wires.WiresManager;
import com.ait.lienzo.client.core.shape.wires.WiresShape;
import com.ait.lienzo.client.core.types.ImageData;
import com.ait.lienzo.client.core.types.PathPartList;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.client.core.types.Point2DArray;
import com.ait.lienzo.client.core.types.Shadow;
import com.ait.lienzo.client.core.util.Geometry;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.ait.lienzo.shared.core.types.ColorName;
import com.ait.tooling.nativetools.client.util.Console;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.ui.RootPanel;
import com.ait.lienzo.client.core.event.NodeMouseClickHandler;

public class Test5
{
    private Layer              layer;

    static WiresManager  m_wiresManager  = WiresManager.getInstance();

    public static void showLienzo()
    {
        GWT.setUncaughtExceptionHandler(new
                                                GWT.UncaughtExceptionHandler()
                                                {
                                                    public void onUncaughtException(Throwable e)
                                                    {
                                                        unwrap(e);
                                                    }

                                                    public Throwable unwrap(Throwable e)
                                                    {
                                                        if (e instanceof UmbrellaException)
                                                        {
                                                            log(e);
                                                        }
                                                        return e;
                                                    }
                                                });

                                        showLienzo3();

    }

    public static void log(Throwable e)
    {
        if (e instanceof UmbrellaException)
        {
            UmbrellaException ue = (UmbrellaException) e;
            for (Throwable t : ue.getCauses())
            {
                log(t);
            }
            return;
        }
        else
        {
            Console.get().info(e.toString());
            for (StackTraceElement el : e.getStackTrace())
            {
                Console.get().info(el.toString());
            }
        }

    }
    public static void showLienzo3()
    {
        final LienzoPanel panel = new LienzoPanel(1000, 1000);
        RootPanel.get().add(panel);

        Test5 test5 = new Test5();
        test5.layer = new Layer();
        panel.add( test5.layer );
        m_wiresManager.init(test5.layer);
        double w, h, x, y;
        w = 100;
        h = 100;

        WiresShape wiresShape0 =  m_wiresManager.createShape(new MultiPath().rect(0, 0, w, h).setStrokeColor("#CC0000"));
        wiresShape0.getGroup().setX(400).setY(400).add(new Circle(30).setX(50).setY(50).setDraggable(true));
        m_wiresManager.createMagnets(wiresShape0);

        WiresShape wiresShape2 =  m_wiresManager.createShape(new MultiPath().rect(0, 0, 400, 200).setStrokeColor("#0000CC"));
        wiresShape2.getGroup().setX(50).setY(100);

        WiresShape wiresShape1 =  m_wiresManager.createShape(new MultiPath().rect(0, 0, w, h).setStrokeColor("#00CC00"));
        wiresShape1.getGroup().setX(50).setY(50).add(new Star(5, 15, 40).setX(50).setY(55));


        m_wiresManager.createMagnets(wiresShape1);
        m_wiresManager.createMagnets(wiresShape2);

        WiresLayer wiresLayer = m_wiresManager.getLayer();

        wiresShape2.add(wiresShape1);
        wiresLayer.add(wiresShape2);
        wiresLayer.add(wiresShape0);

        connect(test5.layer, wiresShape1.getMagnets(), 3, wiresShape0.getMagnets(), 7, m_wiresManager);

        m_wiresManager.addToIndex(wiresShape0);
        m_wiresManager.addToIndex(wiresShape1);
        m_wiresManager.addToIndex(wiresShape2);

        test5.layer.draw();
    }

    private static void connect(Layer layer, IMagnets magnets0,  int i0_1, IMagnets magnets1,  int i1_1, WiresManager wiresManager)
    {

        Magnet m0_1 = (Magnet) magnets0.getMagnet(i0_1);
        Magnet m1_1 = (Magnet) magnets1.getMagnet(i1_1);

        double x0, x1, y0, y1;

        OrthogonalPolyLine line;
        x0 = m0_1.getControl().getX();
        y0 = m0_1.getControl().getY();
        x1 = m1_1.getControl().getX();
        y1 = m1_1.getControl().getY();
        line = createLine(layer, 0, 0, x0, y0, (x0 + ((x1-x0)/2)), (y0 + ((y1-y0)/2)), x1, y1);
        final Connector connector = wiresManager.createConnector(m0_1, m1_1, line);
    }

}