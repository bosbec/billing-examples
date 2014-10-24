namespace Bosbec.CreateSpecificationExample.Helpers
{
    using System;

    using EasyHttp.Http;

    public static class SpecificationsHelper
    {
        public static string List()
        {
            var authenticationToken = AuthenticationHelper.Authenticate();

            var baseUrl = ConfigurationHelper.ApiBaseUrl;

            var client = new HttpClient(baseUrl);

            client.Request.AddExtraHeader("X-Auth-Token", authenticationToken);

            var response = client.Get("api/v2/specifications");

            var specifications = response.RawText;

            return specifications;
        }

        public static void Create(DateTime periodStart, DateTime periodEnd, string notifyUrl)
        {
            var authenticationToken = AuthenticationHelper.Authenticate();

            var baseUrl = ConfigurationHelper.ApiBaseUrl;

            var client = new HttpClient(baseUrl);

            client.Request.AddExtraHeader("X-Auth-Token", authenticationToken);

            var data = new
            {
                periodEnd,
                periodStart,
                notifyUrl
            };

            client.Post("api/v2/specifications", data, "application/json");
        }
    }
}