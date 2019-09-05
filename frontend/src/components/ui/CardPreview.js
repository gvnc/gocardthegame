import React, {Component} from "react";
import { StyleSheet, View, TouchableOpacity} from 'react-native';
import { Text } from 'native-base';
import { Image } from "react-native-expo-image-cache";

import FIcon from 'react-native-vector-icons/FontAwesome';
import apiConfig from '../../store/actions/apiConfig';

import { strings } from '../i18n';

export default class CardPreview extends Component {

    render() {

        var cardIcon = null;
        if(this.props.cardIcon !== undefined){
            if(this.props.cardIcon === 'Unique')
                cardIcon = <FIcon name='star' size={15} color="#ffff4d" style={[StyleSheet.absoluteFillObject, {left:15}]}/>;
            else if(this.props.cardIcon === 'Rare')
                cardIcon = <FIcon name='star' size={15} color="#ffff4d" style={[StyleSheet.absoluteFillObject, {left:15}]}/>;
        }

        return (
            <TouchableOpacity onPress={this.props.onPressed}>
                <View style={styles.outerView}>
                    <Image
                        uri={apiConfig.url + '/images/' + this.props.imagePath}
                        style={[styles.image, this.props.style]} resizeMode="cover" />
                    <Text style={styles.collectionTitle}>{strings(this.props.footerText)}</Text>
                    {cardIcon}
                </View>
            </TouchableOpacity>
        );
    }
}

const styles = StyleSheet.create({
    image: {
        aspectRatio:1.33,
        width:'85%'
    },
    outerView: {
        justifyContent: 'center',
        alignItems: 'center',
        flex: 1
    },
    collectionTitle: {
        fontWeight: "bold",
        fontSize: 17,
        margin: 5
    }
});
