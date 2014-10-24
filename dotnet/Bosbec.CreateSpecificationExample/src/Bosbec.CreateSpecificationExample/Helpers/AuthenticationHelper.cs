namespace Bosbec.CreateSpecificationExample.Helpers
{
    using System;
    using System.Net;

    using EasyHttp.Http;

    public static class AuthenticationHelper
    {
        public static string Authenticate(string username, string password)
        {
            var baseUrl = ConfigurationHelper.ApiBaseUrl;

            if (string.IsNullOrEmpty(baseUrl))
            {
                throw new InvalidOperationException("Missing MobileResponse API base url in configuration file");
            }

            var client = new HttpClient(baseUrl);

            var data = new
            {
                username,
                password
            };

            var response = client.Post("api/v2/authentication-tokens", data, "application/json");

            if (response.StatusCode != HttpStatusCode.OK)
            {
                return null;
            }

            var authenticationToken = (string)response.DynamicBody.id;

            return authenticationToken;
        }

        public static string Authenticate()
        {
            return Authenticate(ConfigurationHelper.Username, ConfigurationHelper.Password);
        }
    }
}