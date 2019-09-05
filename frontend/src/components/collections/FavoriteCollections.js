import React, { Component } from 'react';
import { StyleSheet, View, FlatList, ActivityIndicator } from 'react-native';
import { Text, Card, CardItem, Body, Left } from 'native-base';
import CardPreview from '../ui/CardPreview';
import { connect } from "react-redux";
import { getFavoriteCollections, openSelectedCollection } from "../../store/actions/collectionsAction";

import FIcon from 'react-native-vector-icons/FontAwesome';

import { strings } from '../i18n';
import AppStyles from '../AppStyles';

class FavoriteCollections extends Component {

    render() {

        var flatListObject = null;

        if (this.props.favoriteCollectionList.length > 0) {
            flatListObject = (
                <FlatList horizontal={true} data={this.props.favoriteCollectionList} keyExtractor={this.collectionsKeyExtractor}
                    renderItem={({ item }) => (
                        <CardPreview imagePath={item.collection.capImageUrl + ".jpg"} footerText={item.collection.title}
                            onPressed={() => this.openCollectionHandler(item.collection.id, item.collection.title)}
                            style={{ width: 90 }}
                        />
                    )}
                    ItemSeparatorComponent={() => (<View style={{ width: 20 }}></View>)}
                />);
        } else {
            if (this.props.isLoading) {
                flatListObject = (
                    <Body style={{ justifyContent: 'center', alignItems: 'center' }}>
                        <ActivityIndicator animating={this.props.isLoading && this.props.favoriteCollectionList.length == 0}
                                           size="large" color={AppStyles.primaryColor} />
                    </Body>
                );
            } else {
                flatListObject = (
                    <Body style={{ justifyContent: 'center', alignItems: 'center' }}>
                        <Text style={{ fontSize: 20, marginTop: 10 }}>{strings('collections.favoriteText1')}</Text>
                        <Text style={{ fontSize: 20, marginTop: 5 }}>{strings('collections.favoriteText2')}</Text>
                        <FIcon name='hand-o-down' style={{ fontSize: 25, color: AppStyles.primaryColor, marginTop: 10, alignSelf: 'center' }} />
                    </Body>
                );
            }
        }

        return (
            <Card transparent={true} style={{marginTop:1, marginBottom:0, paddingLeft:0, paddingRight:0, paddingTop:0,
                        paddingBottom:0, height: 175}}>
                <CardItem style={{ justifyContent: 'center', alignItems: 'center',
                         paddingTop:10, paddingBottom:10, backgroundColor: AppStyles.primaryColor }}>
                    <Text style={styles.header}>{strings('collections.favoriteTitle')}</Text>
                </CardItem>
                <CardItem style={{ flex: 1, width:'100%', flexDirection: 'column',
                        alignItems: 'flex-start', backgroundColor: 'rgba(52, 52, 52, 0.18)' }}>
                    {flatListObject}
                </CardItem>
            </Card>
        );
    };

    collectionsKeyExtractor(item, index) {
        return "collection" + index;
    }

    componentDidMount() {
        this.props.getFavoriteCollections();
    };

    openCollectionHandler(collectionId, collectionTitle) {
        this.props.openSelectedCollection(collectionId, collectionTitle);
    }
}

const styles = StyleSheet.create({
    header: {
        fontWeight: 'bold',
        fontSize: 20,
        color: '#FFFFFF'
    },
    outerView: {
        width: '100%',
        marginLeft: 10
    }
});

const mapStateToProps = state => {
    return {
        isLoading: state.uiLoading.favoriteCollections,
        favoriteCollectionList: state.collections.favoriteCollections
    };
};

const mapDispatchToProps = dispatch => {
    return {
        getFavoriteCollections: () => dispatch(getFavoriteCollections()),
        openSelectedCollection: (collectionId, collectionTitle) => dispatch(openSelectedCollection(collectionId, collectionTitle))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(FavoriteCollections);