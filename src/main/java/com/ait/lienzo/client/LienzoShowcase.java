package com.ait.lienzo.client;

//import com.ait.lienzo.charts.client.pie.PieChart;
//import com.ait.lienzo.charts.client.pie.PieChartData;
import com.ait.lienzo.client.DecoratableLine.CornerDecorator;
import com.ait.lienzo.client.DecoratableLine.CornerDecorators;
import com.ait.lienzo.client.DecoratableLine.EndDecorator;
import com.ait.lienzo.client.DecoratableLine.EndDecorators;
import com.ait.lienzo.client.core.Context2D;
import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.ait.lienzo.shared.core.types.LineCap;
import com.ait.lienzo.shared.core.types.LineJoin;
import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * TODO: update me
 */
public class LienzoShowcase implements EntryPoint {

    @Override
    public void onModuleLoad() {

        final Element e = RootPanel.get( "loading" ).getElement();

        new Animation() {

            @Override
            protected void onUpdate( double progress ) {
                e.getStyle().setOpacity( 1.0 - progress );
            }

            @Override
            protected void onComplete() {
                e.getStyle().setVisibility( Style.Visibility.HIDDEN );
                //Test1.showLienzo();

                Test5.showLienzo();
            }
        }.run( 500 );
    }


}
