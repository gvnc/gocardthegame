import React, { Component } from 'react';
import { ImageBackground, Platform, View , Dimensions} from 'react-native';
import {Button, Container, Text, Thumbnail} from 'native-base';

import { openRandomCard, closeCardShow, openCardOfClass, getCardsLeft, getRewardCards } from '../../store/actions/cardAction';
import { checkIfAnyAchievementsAccomplished, checkIfCollectionCompleted } from '../../store/actions/profileActions';

import apiConfig from '../../store/actions/apiConfig';

import { connect } from "react-redux";
import MIcon from 'react-native-vector-icons/MaterialIcons';
import FIcon from 'react-native-vector-icons/FontAwesome';
import FtIcon from 'react-native-vector-icons/Feather';
import CollectionSuggest from './CollectionSuggest';
import CardDetailPopup from '../collections/CardDetailPopup';

import { AdMobRewarded} from 'expo';

import texture from "../../assets/images/texture2.jpg";

import { strings } from '../i18n';

import AppStyles from '../AppStyles';
import TabHeader from "../ui/TabHeader";

class TabCardScreen extends Component {

	cardTypes = ['Daily', 'Rare', 'Unique'];

    windowWidth = 400;
    windowHeight = 400;

	state = {
		selectedCardType: 'Daily',
		selectedCollection: null
	}

	gotoPreviousCardType() {
		let selectedCardType = this.state.selectedCardType;
		let selectedIndex = this.cardTypes.findIndex(cardType => cardType === selectedCardType);

		if (selectedIndex === 0) {
			selectedIndex = this.cardTypes.length - 1;
		} else {
			selectedIndex = selectedIndex - 1;
		}
		selectedCardType = this.cardTypes[selectedIndex];
		this.setState({
			...this.state,
			selectedCardType: selectedCardType
		});
	}

	gotoNextCardType() {
		let selectedCardType = this.state.selectedCardType;
		let selectedIndex = this.cardTypes.findIndex(cardType => cardType === selectedCardType);

		if (selectedIndex === this.cardTypes.length - 1) {
			selectedIndex = 0;
		} else {
			selectedIndex = selectedIndex + 1;
		}
		selectedCardType = this.cardTypes[selectedIndex];
		this.setState({
			...this.state,
			selectedCardType: selectedCardType
		});
	}


	gotoPreviousCollection() {
		let selectedCollection = this.state.selectedCollection;
		let selectedIndex = this.props.favoriteCollectionList.findIndex(collection => collection.id === selectedCollection.id);

		if (selectedIndex === 0) {
			selectedIndex = this.props.favoriteCollectionList.length - 1;
		} else {
			selectedIndex = selectedIndex - 1;
		}
		selectedCollection = this.props.favoriteCollectionList[selectedIndex];
		this.setState({
			...this.state,
			selectedCollection: selectedCollection
		});
	}

	gotoNextCollection() {
		let selectedCollection = this.state.selectedCollection;
		let selectedIndex = this.props.favoriteCollectionList.findIndex(collection => collection.id === selectedCollection.id);

		if (selectedIndex === this.props.favoriteCollectionList.length - 1) {
			selectedIndex = 0;
		} else {
			selectedIndex = selectedIndex + 1;
		}
		selectedCollection = this.props.favoriteCollectionList[selectedIndex];
		this.setState({
			...this.state,
			selectedCollection: selectedCollection
		});
	}

	closeModal() {
		this.props.closeCardShow();
		this.props.checkIfAnyAchievementsAccomplished();
		
		var collectionId = this.state.selectedCollection.collection.id;
		this.props.checkIfCollectionCompleted(collectionId);
	}

	claimCard() {
		var collectionId = this.state.selectedCollection.collection.id;
		var selectedCardType = this.state.selectedCardType;
		if (selectedCardType === "Daily") {
			this.props.openRandomCard(collectionId);
		} else {
			this.props.openCardOfClass(collectionId, selectedCardType);
		}
	}

	watchAdv = async () => {
		if(Platform.OS === 'ios') {
            AdMobRewarded.setAdUnitID(apiConfig.iosRewardedId);
        }else{
            AdMobRewarded.setAdUnitID(apiConfig.androidRewardedId);
		}
        AdMobRewarded.addEventListener('rewardedVideoDidRewardUser', () => this.props.getRewardCards("Daily"));
        await AdMobRewarded.requestAdAsync();
        await AdMobRewarded.showAdAsync();
	}

	componentWillMount() {
		if (this.props.favoriteCollectionList != null && this.props.favoriteCollectionList.length > 0) {
			if (this.state.selectedCollection == null) {
				this.setState({
					...this.state,
					selectedCollection: this.props.favoriteCollectionList[0]
				});
			}
		}
		if (this.props.cardsLeft == null) {
			this.props.getCardsLeft();
		}
	}

    componentDidMount() {
        this.windowWidth = Math.round(Dimensions.get('window').width);
        this.windowHeight = Math.round(Dimensions.get('window').height);
    }

	render() {
		var renderComponent = null;

		if (this.state.selectedCollection == null) {
			if (this.props.favoriteCollectionList != null && this.props.favoriteCollectionList.length > 0) {
				this.setState({
					...this.state,
					selectedCollection: this.props.favoriteCollectionList[0]
				});
			}
		} else {
			if (this.props.favoriteCollectionList == null || this.props.favoriteCollectionList.length == 0) {
				this.setState({
					...this.state,
					selectedCollection: null
				});
			}
		}

		var showWatchAdvButton = false;
		if (this.state.selectedCollection != null) {
			var numOfCardsLeft = 0;
			if (this.props.cardsLeft != null) {
				switch (this.state.selectedCardType) {
					case "Daily":
						numOfCardsLeft = this.props.cardsLeft.numOfDailyCardsLeft;
						if(numOfCardsLeft == 0){
							showWatchAdvButton = true;
						}
						break;
					case "Rare":
						numOfCardsLeft = this.props.cardsLeft.numOfRareCardsLeft;
						break;
					case "Unique":
						numOfCardsLeft = this.props.cardsLeft.numOfUniqueCardsLeft;
						break;
					default:
						break;
				}
			}

			var claimButtonDisabled = true;
			if (numOfCardsLeft > 0) {
				claimButtonDisabled = false;
			}

			let claimButton = null;
			if(showWatchAdvButton == true) {
				claimButton=(
					<View style={{ marginTop: 25, alignItems: 'center' }}>
						<Text style={{ fontSize: 20 }}>{strings("cards.screen.watchAdvText1")}</Text>
						<Text style={{ fontSize: 20 }}>{strings("cards.screen.watchAdvText2")}</Text>
						<FIcon name='hand-o-down' style={{ fontSize: 40, color: AppStyles.primaryColor, marginTop: 10 }} />
						<Button large onPress={this.watchAdv.bind(this)}
							style={{ alignSelf: 'center', marginTop: 10, backgroundColor:AppStyles.secondaryColor }} >
							<Text uppercase={false} style={{ fontSize: 25, color: 'white' }}>{strings("cards.screen.watchAdvButton")}</Text>
						</Button>
					</View>
				);
			} else if (this.props.isOpenCardLoading == false) {
				claimButton = (
					<View style={{ marginTop: 25, alignItems: 'center' }}>
						<Text style={{ fontSize: 20 }}>{strings("cards.screen.text1")}</Text>
						<Text style={{ fontSize: 20 }}>{strings("cards.screen.text2")}</Text>
						<FIcon name='hand-o-down' style={{ fontSize: 40, color: AppStyles.primaryColor, marginTop: 10 }} />
						<Button large disabled={claimButtonDisabled} onPress={this.claimCard.bind(this)}
							style={{ alignSelf: 'center', marginTop: 10}} >
							<Text uppercase={false} style={{ fontSize: 25, color: 'white' }}>{strings("cards.screen.claimButton")}</Text>
						</Button>
					</View>
				);
			} else {
				claimButton = (
                    <View style={{ marginTop: 25, alignItems: 'center' }}>
                        <Text style={{ fontSize: 20 }}>{strings("cards.screen.text1")}</Text>
                        <Text style={{ fontSize: 20 }}>{strings("cards.screen.text2")}</Text>
                        <FtIcon name='loader' style={{ fontSize: 40, color: AppStyles.primaryColor, marginTop: 10 }} />
                        <Button large disabled={true} onPress={this.claimCard.bind(this)}
                                style={{ alignSelf: 'center', marginTop: 10}} >
                            <Text uppercase={false} style={{ fontSize: 25, color: 'white' }}>{strings("cards.screen.claimButton")}</Text>
                        </Button>
                    </View>
				);
			}

			let collectionObject = (
				<View style={{ alignItems: 'center', marginTop: 25 }}>
					<View flexDirection='row' style={{ alignItems: 'center' }}>
						<Button onPress={this.gotoPreviousCollection.bind(this)}>
							<MIcon name='navigate-before' style={{ fontSize: 30, color: 'white' }} />
						</Button>
						<View style={{ width: 230, alignItems: 'center' }}>
							<Text style={{ fontSize: 27, textAlign: 'center'}}>{strings(this.state.selectedCollection.collection.title)}</Text>
						</View>
						<Button onPress={this.gotoNextCollection.bind(this)}>
							<MIcon name='navigate-next' style={{ fontSize: 30, color: 'white' }} />
						</Button>
					</View>
					<Thumbnail source={{ uri: apiConfig.url + '/images/' + this.state.selectedCollection.collection.capImageUrl + ".jpg" }} />
					<View style={{ height: 3, width: 280, backgroundColor: AppStyles.primaryColor, marginTop: 10 }}></View>
				</View>);

			renderComponent = (
				<View style={{ flex: 1, justifyContent: 'center' }}>
					<View style={{ alignItems: 'center' }}>
						<View flexDirection='row' style={{ alignItems: 'center' }}>
							<Button onPress={this.gotoPreviousCardType.bind(this)}>
								<MIcon name='navigate-before' style={{ fontSize: 30, color: 'white' }} />
							</Button>
							<View style={{ width: 230, alignItems: 'center' }}>
								<Text style={{ fontSize: 27}}>{strings("cards.screen." + this.state.selectedCardType)}</Text>
							</View>
							<Button onPress={this.gotoNextCardType.bind(this)}>
								<MIcon name='navigate-next' style={{ fontSize: 30, color: 'white' }} />
							</Button>
						</View>
						<Text style={{ fontSize: 15, marginTop: 10, color:AppStyles.secondaryColor }}>{numOfCardsLeft} {strings("cards.screen.cardsLeft")}</Text>
						<View style={{ height: 3, width: 280, backgroundColor: AppStyles.primaryColor, marginTop: 10 }}></View>
					</View>
					{collectionObject}
					{claimButton}
				</View>
			);
		} else {
			renderComponent = <CollectionSuggest />;
		}

		return (
            <Container style={{ flex: 1, justifyContent: 'center', alignContent: 'center'}}>
                <ImageBackground source={texture} style={{ width: "100%", flex: 1 }}>
                    <View style={{ flex: 1}}>
						<TabHeader title={strings("tabBar.cards")} />
						{renderComponent}
						<CardDetailPopup
							id={this.props.openedCard !== null ? this.props.openedCard.id: 0}
							title={this.props.openedCard !== null ? this.props.openedCard.title: ''}
							imageUrl={this.props.openedCard !== null ? this.props.openedCard.imageUrl: 'emptyImage'}
							setVisible={this.closeModal.bind(this)}
							points={this.props.openedCard !== null ? this.props.openedCard.points: 0}
							cardClass={this.props.openedCard !== null ? this.props.openedCard.cardClass: ''}
							visible={this.props.openedCard !== null ? this.props.showOpenedCard : false}
							windowWidth={this.windowWidth}
                            windowHeight={this.windowHeight}/>
						</View>
                </ImageBackground>
            </Container>
		);
	}
}

const mapStateToProps = state => {
	return {
		isOpenCardLoading: state.uiLoading.openCard,
		showOpenedCard: state.card.showOpenedCard,
		openedCard: state.card.openedCard,
		cardsLeft: state.card.cardsLeft,
		favoriteCollectionList: state.collections.favoriteCollections
	};
};

const mapDispatchToProps = dispatch => {
	return {
		openRandomCard: (collectionId) => dispatch(openRandomCard(collectionId)),
		openCardOfClass: (collectionId, cardClass) => dispatch(openCardOfClass(collectionId, cardClass)),
		getCardsLeft: () => dispatch(getCardsLeft()),
		closeCardShow: () => dispatch(closeCardShow()),
		checkIfAnyAchievementsAccomplished: () => dispatch(checkIfAnyAchievementsAccomplished()),
		checkIfCollectionCompleted: (collectionId) => dispatch(checkIfCollectionCompleted(collectionId)),
		getRewardCards: (cardClass) => dispatch(getRewardCards(cardClass))
	};
};

export default connect(mapStateToProps, mapDispatchToProps)(TabCardScreen);