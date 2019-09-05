import React, { Component } from 'react';
import { View, PanResponder, ImageBackground } from 'react-native';

import apiConfig from '../../store/actions/apiConfig';

export default class StripCard extends Component {

    pixelSize = 0;
    viewWidth = 0;
    viewHeight = 0;

    colSize = 0;
    rowSize = 0;

    startX = 0;
    startY = 0;

    gridMatrix = [];

    constructor(props) {

        super(props);

        this.pixelSize = props.pixelSize;
        this.viewWidth = props.width;
        this.viewHeight = props.height;

        this.state = {};

        this.state = { moveX: 0, moveY: 0 };

        this.colSize = this.viewWidth / this.pixelSize;
        this.rowSize = this.viewHeight / this.pixelSize;

        var rowNum;
        var colNum;

        for (rowNum = 0; rowNum < this.rowSize; rowNum++) {
            var colArray = [];
            for (colNum = 0; colNum < this.colSize; colNum++) {
                colArray.push(0);
            }
            this.gridMatrix.push(colArray);
        }

        this.handleLayoutChange = this.handleLayoutChange.bind(this);
    }

    panResponder = PanResponder.create({
        onMoveShouldSetResponderCapture: () => true,
        onMoveShouldSetPanResponderCapture: () => true,

        onPanResponderMove: (event, gesture) => {
            this.setPosition(gesture);
        },

        onPanResponderRelease: (evt, gestureState) => {

        },
    });

    setPosition(gesture) {

        gridX = gesture.moveX - this.startX;
        gridY = gesture.moveY - this.startY;

        var pixelX = Math.floor(gridX / this.pixelSize);
        var pixelY = Math.floor(gridY / this.pixelSize);
        //this.brush(pixelX, pixelY);
        //this.brush2(pixelX, pixelY);
        this.brush3(pixelX, pixelY);

        this.setState({
            moveX: gesture.moveX,
            moveY: gesture.moveY
        });
    }

    brush(x,y){
        brushSize = 8;
        
        var tmpX = x;
        var tmpY = y - brushSize;

        for(var i=0;i<=brushSize;i++) {

            if(i === 0) {
                this.setGridXY(tmpX, tmpY);
            } else {
                var tmpX = x-i;
                maxGoX = x + i;
                for(tmpX; tmpX <= maxGoX; tmpX++) {
                    this.setGridXY(tmpX, tmpY);
                }
            }
            tmpY = tmpY + 1;
        }
        
        var tmpX = x;
        var tmpY = y + brushSize;

        for(var i=0;i<=brushSize;i++) {

            if(i === 0) {
                this.setGridXY(tmpX, tmpY);
            } else {
                var tmpX = x-i;
                maxGoX = x + i;
                for(tmpX; tmpX <= maxGoX; tmpX++) {
                    this.setGridXY(tmpX, tmpY);
                }
            }
            tmpY = tmpY - 1;
        }
    }

    
    brush2(x,y){
        brushSizeX = 4;
        brushSizeY = 3;

        var tmpY = y - brushSizeY;
        var tmpX = x + brushSizeY;
        
        var maxGoY = y + brushSizeY;
        for(tmpY; tmpY <= maxGoY; tmpY++){

            var tmpX1 = tmpX - brushSizeX;
            var maxGoX = tmpX + brushSizeX;
            for(tmpX1; tmpX1 <= maxGoX; tmpX1++){
                this.setGridXY(tmpX1, tmpY);
            }
            tmpX --;
        }
    }

    
    brush3(x,y){
        this.setGridXY(x, y);
    }

    setGridXY(x, y){
        if (x >= 0 && y >= 0 && y < this.gridMatrix.length) {
            if (x < this.gridMatrix[y].length) {
                this.gridMatrix[y][x] = 1;
            }
        }
    }


    handleLayoutChange() {
        this.stripCardView.measure((fx, fy, width, height, px, py) => {
            this.startX = px;
            this.startY = py;
        })
    }

    render() {
        let fullGrid = this.gridMatrix.map((cols, rowIndex) => {

            var viewRowKey = "view" + rowIndex;
            let gridRow = cols.map((isTransparent, colIndex) => {
                var viewColKey = "view" + rowIndex + "C" + colIndex;
                if (isTransparent === 1)
                    return <View key={viewColKey} style={{ width: this.pixelSize, height: this.pixelSize }} />;
                else
                    return <View key={viewColKey} style={{ width: this.pixelSize, height: this.pixelSize, backgroundColor: 'grey' }} />;
            })

            return <View key={viewRowKey} flexDirection='row'>{gridRow}</View>;
        })

        return (
            <View
                style={{ marginTop: 25, height: this.viewHeight, width: this.viewWidth }}
                ref={view => { this.stripCardView = view; }}
                onLayout={(event) => { this.handleLayoutChange(event) }}
                {...this.panResponder.panHandlers}
            >
                <ImageBackground
                    source={{ uri: apiConfig.url + '/images/' + this.props.imageUrl }}
                    style={{ height: this.viewHeight, width: this.viewWidth }}>
                    {fullGrid}
                </ImageBackground>
            </View>
        );
    }
}