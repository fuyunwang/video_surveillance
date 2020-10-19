import defaultSettings from '@/settings'

const title = defaultSettings.title || 'Chuoyue——绰约'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
