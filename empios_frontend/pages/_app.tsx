import { AppProps } from 'next/app'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { DataProvider } from '../context/DataContext' 

// Create a new instance of QueryClient
const queryClient = new QueryClient()

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <QueryClientProvider client={queryClient}>
      <DataProvider>
        <Component {...pageProps} />
      </DataProvider>
    </QueryClientProvider>
  )
}

export default MyApp
