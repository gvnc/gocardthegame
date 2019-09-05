import ReactNative from 'react-native';
import I18n from 'ex-react-native-i18n';

import en from '../assets/locales/en.json';
import tr from '../assets/locales/tr.json';
import es from '../assets/locales/es.json';
import it from '../assets/locales/it.json';

// Should the app fallback to English if user locale doesn't exists
I18n.fallbacks = true;

// default keys are loadded here, 
// dynamic data will be loaded from server on startup
I18n.translations = {
	en,
	tr,
	es,
	it
};
const currentLocale = I18n.currentLocale();

// Is it a RTL language?
export const isRTL = currentLocale.indexOf('he') === 0 || currentLocale.indexOf('ar') === 0;

// Allow RTL alignment in RTL languages
ReactNative.I18nManager.allowRTL(isRTL);

// The method we'll use instead of a regular string
export function strings(name, params = {}) {
	var translatedValue = I18n.t(name, params);
	if(translatedValue.indexOf("missing") > 0 && translatedValue.indexOf("translation") > 0)
		return name;
	return translatedValue;
};

export function getLocale() {
	return I18n.currentLocale();
};

export function addLocale(localeName, localeObject) {
	I18n.translations[localeName] = {
		...I18n.translations[localeName],
		data: localeObject
	}
};

export default I18n;