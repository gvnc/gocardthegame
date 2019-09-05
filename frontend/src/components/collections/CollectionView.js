import React, { Component } from 'react';

import {Dimensions, ImageBackground, ScrollView, TouchableWithoutFeedback} from 'react-native';
import { Container, View, Text, Grid, Row, Col, Card, CardItem, Button } from 'native-base';
import { connect } from "react-redux";

import MIcon from 'react-native-vector-icons/MaterialIcons';

import { addCollectionToFavorites, removeCollectionFromFavorites } from "../../store/actions/collectionsAction";
import { setHintDisplayed } from "../../store/actions/authActions";
import NavigationService from '../../store/actions/NavigationService';

import texture from "../../assets/images/texture2.jpg";
import { strings } from '../i18n';

import AppStyles from '../AppStyles';
import CardDetailPopup from "./CardDetailPopup";
import CardPreview from '../ui/CardPreview';
import CollectionInfoPopup from "./CollectionInfoPopup";
import CollectionHintPopup from "./CollectionHintPopup";

class CollectionView extends Component {

	state = {
		selectedCardId: 0,
		selectedCardTitle: 'No Card Selected',
		modalVisible: false,
        infoModalVisible: false,
        hintModalVisible: false
	};

    windowWidth = 400;
    windowHeight = 400;

    componentDidMount() {
        this.windowWidth = Math.round(Dimensions.get('window').width);
        this.windowHeight = Math.round(Dimensions.get('window').height);
    }

	render() {
		let thisClass = this;

		let cardsView = <Row><Text>No cards available yet.</Text></Row>;

		var addToFavsButtonEnabled = true;
		if (this.props.favoriteCollectionList != null) {
			var selectedCollection = this.props.favoriteCollectionList.filter(function (accountCollection) {
				return accountCollection.collection.id === thisClass.props.collectionId;
			});
			if (selectedCollection != null && selectedCollection.length > 0)
				addToFavsButtonEnabled = false;
		}

		var collectionObject = null;
        if (this.props.allCollections !== null) {
            var collectionObject = this.props.allCollections.find(function (collection) {
                return collection.id === thisClass.props.collectionId;
            });
        }

        var openedCardIds = [];
        var cardsOpened = 0;
        if(this.props.openedCardIdList !== null){
            var openedCardsObject = this.props.openedCardIdList.find(function (openedCardsObj) {
                return openedCardsObj.collectionId === thisClass.props.collectionId;
            });
            if(openedCardsObject !== undefined && openedCardsObject !== null) {
                cardsOpened = openedCardsObject.cardIds.length;
                openedCardIds = openedCardsObject.cardIds;
			}
        }

        var collectionObjectData = this.props.collectionObjectList.find(function(collectionObjectD){
            return collectionObjectD.id === thisClass.props.collectionId
        });

        var collectionCards = null;
        if(collectionObjectData !== undefined && collectionObjectData !== null)
            collectionCards = collectionObjectData.cards;

		if (collectionCards != null && collectionCards.length > 0) {

			cardsView = collectionCards.map(function (card, index) {
				if (index % 2 == 0) {

					let rowIndex = "cardsRow" + index;

					if (index == collectionCards.length - 1) {
						// only 1 column left
						let imageUrl = card.imageUrl + "_grey";
						if(openedCardIds.indexOf(card.id) > -1) {
							imageUrl = card.imageUrl;
						}

						return (
							<Row style={{ marginTop: 15 }} key={rowIndex}>
								<Col>
									<CardPreview imagePath={imageUrl + ".jpg"} footerText={card.title} cardIcon={card.cardClass}
										onPressed={() => thisClass.cardDetailHandler(card, imageUrl)} />
								</Col>
								<Col>

								</Col>
							</Row>
						);
					} else {
						// print this and next column
						let nextCard = collectionCards[index + 1];
						let imageUrl = card.imageUrl + "_grey";
                        if(openedCardIds.indexOf(card.id) > -1) {
                            imageUrl = card.imageUrl;
						}
						let nextImageUrl = nextCard.imageUrl + "_grey";
                        if(openedCardIds.indexOf(nextCard.id) > -1) {
							nextImageUrl = nextCard.imageUrl;
						}
						return (
							<Row style={{ marginTop: 8 }} key={rowIndex}>
								<Col>
									<CardPreview imagePath={imageUrl + ".jpg"} footerText={card.title} cardIcon={card.cardClass}
										onPressed={() => thisClass.cardDetailHandler(card, imageUrl)} />
								</Col>
								<Col>
									<CardPreview imagePath={nextImageUrl + ".jpg"} footerText={nextCard.title} cardIcon={nextCard.cardClass}
										onPressed={() => thisClass.cardDetailHandler(nextCard, nextImageUrl)} />
								</Col>
							</Row>
						);
					}
				}
			});
		}
		var favsButtonComponent = null;
		if (addToFavsButtonEnabled == true) {
			favsButtonComponent = (
				<Button transparent onPress={() => this.addCollectionToFavorites(this.props.collectionId)} >
					<Text uppercase={false} style={{ fontSize: 20, paddingRight:4, color:AppStyles.secondaryColor}}>{strings("collections.like")}</Text>
					<MIcon name='favorite-border' style={{ fontSize: 23, color: AppStyles.secondaryColor }} />
				</Button>
			);
		} else {
			favsButtonComponent = (
				<Button transparent onPress={() => this.removeCollectionFromFavorites(this.props.collectionId)} >
					<Text uppercase={false} style={{ fontSize: 20, paddingRight:4, color:AppStyles.secondaryColor}}>{strings("collections.like")}</Text>
					<MIcon name='favorite' style={{ fontSize: 23, color: AppStyles.secondaryColor }} />
				</Button>
			);
		}

		return (
			<Container style={{ flex: 1, justifyContent: 'center', alignContent: 'center'}}>
				<ImageBackground source={texture} style={{ width: "100%", flex: 1 }}>
					<View style={{ flex: 1, marginBottom:100 }}>
						<Card transparent style={{ marginTop: 1, paddingLeft:0, paddingRight:0, paddingTop:0,
                            paddingBottom:0, flex: 1, backgroundColor: 'rgba(52, 52, 52, 0)' }}>
							<CardItem bordered style={{ paddingTop:10, paddingBottom:10, justifyContent: 'center', alignItems: 'center', backgroundColor: AppStyles.primaryColor }}
                                      button onPress={() => this.navigateToCollectionListView()} >
									<Text style={{ fontSize: 20, fontWeight: 'bold', color: '#FFFFFF' }}>{strings("tabBar.collections")}</Text>
							</CardItem>
							<CardItem bordered style={{  paddingTop:5, paddingBottom:0, width: '100%', flexDirection: 'row', justifyContent: 'flex-end', alignItems: 'center', backgroundColor: AppStyles.panelBackground}}>
                                <TouchableWithoutFeedback onPress={() => this.setInfoModalVisible(true)} >
                                    <View style={{ flex: 1,flexDirection: 'row'}}>
                                        <MIcon name='menu' style={{ fontSize: 25, color: AppStyles.secondaryColor }} />
                                        <Text style={{ flex: 1, fontSize: 20, fontWeight: 'bold', marginLeft:15, color: AppStyles.secondaryColor }}>{strings(this.props.collectionTitle)}</Text>
                                    </View>
                                </TouchableWithoutFeedback>
								{favsButtonComponent}
							</CardItem>
							<CardItem style={{ backgroundColor: 'rgba(52, 52, 52, 0)' }}>
								<ScrollView style={{flex:1}}>
									<Grid>
										{cardsView}
									</Grid>
								</ScrollView>
							</CardItem>
						</Card>
						<CardDetailPopup
							id={this.state.selectedCardId}
							title={this.state.selectedCardTitle}
							imageUrl={this.state.selectedCardImageUrl}
							setVisible={this.setModalVisible.bind(this)}
							points={this.state.selectedCardPoints}
							cardClass={this.state.selectedCardClass}
							visible={this.state.modalVisible}
							windowWidth={this.windowWidth}
                            windowHeight={this.windowHeight}/>
                        <CollectionInfoPopup visible={this.state.infoModalVisible} setVisible={this.setInfoModalVisible.bind(this)}
											 collection={collectionObject} cardsOpened={cardsOpened} />
                        <CollectionHintPopup visible={this.state.hintModalVisible} setVisible={this.setHintModalVisible.bind(this)} />
					</View>
				</ImageBackground>
			</Container>
		);
	}

	navigateToCollectionListView = () => {
		NavigationService.navigate("CollectionListView");
	}

	backToCollections = () => {
		NavigationService.navigate("CollectionListView")
	};

	setModalVisible(visible) {
		this.setState({
			...this.state,
			modalVisible: visible
		});
	}

    setInfoModalVisible(visible) {
        this.setState({
            ...this.state,
            infoModalVisible: visible
        });
    }

    setHintModalVisible(visible) {
        this.setState({
            ...this.state,
            hintModalVisible: visible
        });
    }

	cardDetailHandler(card, imageUrl) {
		this.setState(
			{
				...this.state,
				selectedCardId: card.id,
				selectedCardTitle: card.title,
				selectedCardImageUrl: imageUrl,
				selectedCardDescription: card.description,
				selectedCardPoints: card.points,
				selectedCardClass: card.cardClass,
				modalVisible: true
			}
		);
	}

	addCollectionToFavorites(collectionId) {
		if(this.props.collectionHintDisplayed === undefined || this.props.collectionHintDisplayed === false) {
            this.setHintModalVisible(true);
			this.props.setHintDisplayed(true);
		}
		this.props.addToFavorites(collectionId);
	}

	removeCollectionFromFavorites(collectionId) {
		this.props.removeFromFavorites(collectionId);
	}

}

const mapStateToProps = state => {
	return {
		collectionId: state.collections.openedCollection.id,
		collectionTitle: state.collections.openedCollection.title,
        collectionObjectList: state.collections.collectionObjectList,
        openedCardIdList: state.card.openedCardIdList,
		selectedCardId: state.selectedCardId,
		selectedCardTitle: state.selectedCardTitle,
		favoriteCollectionList: state.collections.favoriteCollections,
        allCollections: state.collections.allCollections,
        collectionHintDisplayed: state.auth.collectionHintDisplayed
	};
};

const mapDispatchToProps = dispatch => {
	return {
		addToFavorites: (collectionId) => dispatch(addCollectionToFavorites(collectionId)),
		removeFromFavorites: (collectionId) => dispatch(removeCollectionFromFavorites(collectionId)),
        setHintDisplayed: (newValue) => dispatch(setHintDisplayed(newValue))
	};
};

export default connect(mapStateToProps, mapDispatchToProps)(CollectionView);