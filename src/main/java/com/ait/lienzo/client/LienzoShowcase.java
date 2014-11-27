package com.ait.lienzo.client;

import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.core.types.Shadow;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.ait.lienzo.shared.core.types.ColorName;
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
                showLienzo();
            }
        }.run( 500 );
    }

    private void showLienzo() {
        final LienzoPanel panel = new LienzoPanel( 400, 300 );
        RootPanel.get().add( panel );

        Text text = new Text( "Hello World!", "Verdana, sans-serif", "italic bold", 40 );
        text.setX( 10 ).setY( 100 );
        text.setFillColor( ColorName.CORNFLOWERBLUE );
        text.setStrokeColor( ColorName.BLUE );
        text.setShadow( new Shadow( ColorName.DARKMAGENTA, 6, 4, 4 ) );

        Layer layer = new Layer();
        panel.add( layer );

        layer.add( text );
        layer.draw();
    }
}
