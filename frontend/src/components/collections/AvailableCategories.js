import React, { Component } from 'react';
import {StyleSheet, ScrollView, ActivityIndicator, FlatList, View} from 'react-native';
import {Segment, Button, Text, Grid, Row, Col, Card, CardItem, Left, Right, Body} from 'native-base';
import { connect } from "react-redux";
import { getAllCategories, setSelectedCategory, openSelectedCollection } from "../../store/actions/collectionsAction";

import { strings } from '../i18n';
import AppStyles from "../AppStyles";
import {Image} from "react-native-expo-image-cache";
import apiConfig from "../../store/actions/apiConfig";

class AvailableCategories extends Component {

    render() {
        let thisClass = this; // below map statement has its own this object, so to avoid conflict save this object to a new variable

        let categoryButtons = this.props.categoryList.map(function (category) {
            let categoryButtonId = "categoryButton" + category.id;
            return (
                // detect last index and put last statement into button
                <Button style={styles.segmentButton} key={categoryButtonId}
                    active={thisClass.props.selectedCategory.id === category.id ? true : false}
                    onPress={() => thisClass.props.setSelectedCategory(category.id, category.title)}>
                    <Text uppercase={false} style={{fontWeight: 'bold'}}>{strings(category.title)}</Text>
                </Button>);
        });

        let availableCollections = null;

        if (this.props.collectionList != null && this.props.collectionList.length > 0) {
            availableCollections = (
                <FlatList data={this.props.collectionList} keyExtractor={this.collectionsKeyExtractor}
                      renderItem={({ item }) => (
                          <Card style={{ backgroundColor: AppStyles.panelBackground, marginBottom:1, marginTop:2 }}>
                              <CardItem style={{ backgroundColor: AppStyles.panelBackground, paddingLeft:5, paddingTop:5 , paddingBottom:5}}
                                        bordered button onPress={() => this.openCollectionHandler(item.id, item.title)}>
                                  <Left>
                                      <Image
                                          uri={apiConfig.url + '/images/' + item.capImageUrl + ".jpg"}
                                          style={{aspectRatio:1.33, width:70}} resizeMode="cover" />
                                      <Body>
                                          <Text>{strings(item.title)} </Text>
                                      </Body>
                                      <Body style={{flexDirection: 'row', justifyContent: 'flex-end'}}>
                                            <Text style={{color:AppStyles.secondaryColor}}>{item.numOfCards} {strings('collections.cards')}</Text>
                                      </Body>
                                  </Left>
                              </CardItem>
                          </Card>
                      )}
                />
            );
        }

        var activityIndicator = null;
        if (this.props.isLoading && this.props.collectionList.length == 0) {
            activityIndicator = <ActivityIndicator size="large" color={AppStyles.primaryColor} />;
        }

        return (
            <Card transparent={true} style={{flex: 1, marginTop:0, paddingTop:0}}>
                <CardItem style={{ justifyContent: 'center', alignItems: 'center', paddingLeft:1, paddingRight:1,
                        paddingTop:0 , paddingBottom:0, backgroundColor: AppStyles.primaryColor }}>
                    <ScrollView style={styles.scrollView} horizontal={true}>
                        <Segment style={styles.segment} >
                            <Button style={styles.segmentButton}
                                    active={this.props.selectedCategory.id === 0 ? true : false}
                                    onPress={() => this.props.setSelectedCategory(0, 'All')}>
                                <Text uppercase={false} style={{fontWeight: 'bold'}}>{strings('collections.all')}</Text>
                            </Button>
                            {categoryButtons}
                        </Segment>
                    </ScrollView>
                </CardItem>

                <CardItem style={{ justifyContent: 'center', alignItems: 'center',
                            paddingTop:5 , paddingBottom:40 , backgroundColor: 'rgba(52, 52, 52, 0)' }}>
                    <ScrollView style={{backgroundColor: 'rgba(52, 52, 52, 0)' }} >
                        <Grid>
                            {availableCollections}
                        </Grid>
                        {activityIndicator}
                    </ScrollView>
                </CardItem>
            </Card>
        );
    };

    collectionsKeyExtractor(item, index) {
        return "availColls" + index;
    }

    componentDidMount() {
        this.props.getAllCategories();
    };

    openCollectionHandler(collectionId, collectionTitle) {
        this.props.openSelectedCollection(collectionId, collectionTitle);
    }
}

const styles = StyleSheet.create({
    header: {
        fontWeight: 'bold',
        fontSize: 20,
        color: 'white'
    },
    segmentButton: {
        width: 125,
        height: 40,
        justifyContent: "center",
        alignItems: "center"
    },
    scrollView: {
        width: '100%'
    },
    segment: {
        height: 40
    }
});

const mapStateToProps = state => {
    return {
        isLoading: state.uiLoading.availableCategories,
        collectionList: state.collections.availableCollections,
        categoryList: state.collections.categoryList,
        selectedCategory: state.collections.selectedCategory
    };
};

const mapDispatchToProps = dispatch => {
    return {
        getAllCategories: () => dispatch(getAllCategories()),
        setSelectedCategory: (categoryId, categoryTitle) => dispatch(setSelectedCategory(categoryId, categoryTitle)),
        openSelectedCollection: (collectionId, collectionTitle) => dispatch(openSelectedCollection(collectionId, collectionTitle))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(AvailableCategories);